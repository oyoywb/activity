package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.dao.DynamicsCommentRepository;
import com.oywb.weixin.activities.dao.DynamicsRepository;
import com.oywb.weixin.activities.dao.LikesRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.DyCommentReqDto;
import com.oywb.weixin.activities.dto.request.DynamicsRequestDto;
import com.oywb.weixin.activities.entity.*;
import com.oywb.weixin.activities.service.DynamicsService;
import com.oywb.weixin.activities.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicsServiceImpl implements DynamicsService {
    private final DynamicsRepository dynamicsRepository;
    private final static String DY_BUCKET = "dynamics";
    private final Minio minio;
    private final MinioConfig minioConfig;
    private final EntityManager entityManager;
    private final LikesRepository likesRepository;
    private final DynamicsCommentRepository dynamicsCommentRepository;
    private final UserService userService;

    public DynamicsServiceImpl(DynamicsRepository dynamicsRepository, Minio minio, MinioConfig minioConfig, EntityManager entityManager, LikesRepository likesRepository, DynamicsCommentRepository dynamicsCommentRepository, UserService userService) {
        this.dynamicsRepository = dynamicsRepository;
        this.minio = minio;
        this.minioConfig = minioConfig;
        this.entityManager = entityManager;
        this.likesRepository = likesRepository;
        this.dynamicsCommentRepository = dynamicsCommentRepository;
        this.userService = userService;
    }

    @Override
    public void createDynamics(DynamicsRequestDto dynamicsRequestDto, List<MultipartFile> files, String openId) {
        DynamicsEntity dynamicsEntity = dynamicsRequestDto.toDynamicsEntity();
        dynamicsEntity.setUserId(userService.getUserId(openId));
        dynamicsEntity.setCreateTs(new Timestamp(System.currentTimeMillis()));
        dynamicsEntity.setPass((byte)1);

        List<String> fileNames = new ArrayList<>();
        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            minio.upload(fileName, DY_BUCKET, file);
            fileNames.add(minioConfig.getEndpoint() + "/" + DY_BUCKET + "/" + fileName);
        });
        dynamicsEntity.setPicture(String.join(",", fileNames));
        dynamicsRepository.save(dynamicsEntity);
    }

    @Override
    public Page<DynamicsSimpleEntity> getDynamics(Pageable pageable, String tag, String openId, boolean personal) {
        long userId = userService.getUserId(openId);

        StringBuffer sql = new StringBuffer("select dy.*,u.name, u.profile,u.sex,(select count(*) from dynamics_comment dc where dc.dy_id = dy.id) as count, COALESCE(lk.likes, 0) as is_likes , (select count(*) from likes where lk.dy_id = dy.id) as likes from dynamics dy LEFT " +
                "JOIN likes lk ON dy.id = lk.dy_id LEFT JOIN user u ON dy.user_id = u.id where dy.pass = 1 ");
        if (personal) {
            sql.append(" and dy.user_id = :userId");
        }
        if (tag != null) {
            sql.append(" and dy.keyword like '%" + tag + "%'");
        }
        sql.append(" ORDER BY dy.create_ts DESC");

        Query query = entityManager.createNativeQuery(sql.toString());
        if (personal) {
            query.setParameter("userId", userId);
        }
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());

        List<DynamicsSimpleEntity> dynamicsSimpleEntities = query.getResultList();

        return new PageImpl<>(dynamicsSimpleEntities, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), dynamicsSimpleEntities.size());
    }

    @Override
    public void likes(String openId, boolean likes, long id) {
        long userId = userService.getUserId(openId);
        LikesEntity likesEntity = likesRepository.getByUserIdAndProjectId(userId, id);
        if (likesEntity == null) {
            likesEntity = new LikesEntity();
            likesEntity.setUserId(userId);
            likesEntity.setDyId(id);
        }
        likesEntity.setLikes((byte) (likes ? 1 : 0));
        likesRepository.save(likesEntity);
    }

    @Override
    public List<DyCommentSimple> getComment(long id) {
        String sql = "WITH RECURSIVE comment_tree AS (\n" +
                "  SELECT\n" +
                "    id,\n" +
                "    user_id,\n" +
                "    content,\n" +
                "    parent_id,\n" +
                "    created_at,\n" +
                "    updated_at,\n" +
                "    is_deleted,\n" +
                "    deleted_at,\n" +
                "    is_approved,\n" +
                "    dy_id,\n" +
                "    1 AS level\n" +
                "  FROM dynamics_comment\n" +
                "  WHERE parent_id IS NULL AND is_deleted = FALSE AND is_approved = TRUE\n" +
                "  UNION ALL\n" +
                "  SELECT\n" +
                "    dynamics_comment.id,\n" +
                "    dynamics_comment.user_id,\n" +
                "    dynamics_comment.content,\n" +
                "    dynamics_comment.parent_id,\n" +
                "    dynamics_comment.created_at,\n" +
                "    dynamics_comment.updated_at,\n" +
                "    dynamics_comment.is_deleted,\n" +
                "    dynamics_comment.deleted_at,\n" +
                "    dynamics_comment.is_approved,\n" +
                "    dynamics_comment.dy_id,\n" +
                "    comment_tree.level + 1 AS level\n" +
                "  FROM dynamics_comment\n" +
                "  JOIN comment_tree ON dynamics_comment.parent_id = comment_tree.id\n" +
                "  WHERE dynamics_comment.is_deleted = FALSE AND dynamics_comment.is_approved = TRUE\n" +
                ")\n" +
                "SELECT\n" +
                "  id,\n" +
                "  user_id,\n" +
                "  content,\n" +
                "  parent_id,\n" +
                "  created_at,\n" +
                "  updated_at,\n" +
                "  is_deleted,\n" +
                "  deleted_at,\n" +
                "  is_approved,\n" +
                "  dy_id,\n" +
                "  level\n" +
                "FROM comment_tree where dy_id=:dyId\n" +
                "ORDER BY level, created_at";
;
        Query query = entityManager.createNativeQuery(sql, "DyCommentSimple");
        query.setParameter("dyId", id);
        List<DyCommentSimple> dyCommentSimples = query.getResultList();

        return dyCommentSimples;
    }

    @Override
    public void createComment(String openId, DyCommentReqDto dyCommentReqDto) {
        UserEntity userEntity = userService.findByOpenid(openId);
        DynamicsCommentEntity dynamicsCommentEntity = dyCommentReqDto.toDynamicsCommentEntity();
        dynamicsCommentEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        dynamicsCommentEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        dynamicsCommentEntity.setUserId(userEntity.getId());
        dynamicsCommentEntity.setUserName(userEntity.getName());
        dynamicsCommentRepository.save(dynamicsCommentEntity);
    }

    @Transactional
    @Override
    public void deleteDynamics(String openId, long id) {
        long userId = userService.getUserId(openId);
        dynamicsCommentRepository.deleteDynamicsComment(id);
        dynamicsRepository.deleteDynamicsLikes(id);
        dynamicsRepository.deleteDynamics(userId, id);
    }

    @Override
    public void deleteDynamicsComment(long dyCommentId) {
        dynamicsCommentRepository.deleteById(dyCommentId);
    }

    @Override
    public List<DynamicsCommentEntity> getCommentReceive(String openId) {
        long userId = userService.getUserId(openId);

        List<DynamicsCommentEntity> dynamicsCommentEntities = dynamicsCommentRepository.getDynamicsCommentEntitiesByUserId(userId);

        return dynamicsCommentEntities;
    }

    @Override
    public List<DynamicsCommentEntity> getCommentMyself(String openId) {
        long userId = userService.getUserId(openId);

        List<DynamicsCommentEntity> dynamicsCommentEntities = dynamicsCommentRepository.getDynamicsCommentEntitiesMySelf(userId);

        return dynamicsCommentEntities;
    }
}
