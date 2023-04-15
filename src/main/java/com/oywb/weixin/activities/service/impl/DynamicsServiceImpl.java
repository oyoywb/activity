package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.dao.DynamicsCommentRepository;
import com.oywb.weixin.activities.dao.DynamicsRepository;
import com.oywb.weixin.activities.dao.LikesRepository;
import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.DyCommentReqDto;
import com.oywb.weixin.activities.dto.request.DynamicsRequestDto;
import com.oywb.weixin.activities.entity.*;
import com.oywb.weixin.activities.service.DynamicsService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicsServiceImpl implements DynamicsService {
    private final DynamicsRepository dynamicsRepository;
    private final static String DY_BUCKET = "dynamics";
    private final Minio minio;
    private final MinioConfig minioConfig;
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final LikesRepository likesRepository;
    private final DynamicsCommentRepository dynamicsCommentRepository;

    public DynamicsServiceImpl(DynamicsRepository dynamicsRepository, Minio minio, MinioConfig minioConfig, UserRepository userRepository, EntityManager entityManager, LikesRepository likesRepository, DynamicsCommentRepository dynamicsCommentRepository) {
        this.dynamicsRepository = dynamicsRepository;
        this.minio = minio;
        this.minioConfig = minioConfig;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.likesRepository = likesRepository;
        this.dynamicsCommentRepository = dynamicsCommentRepository;
    }

    @Override
    public CommonResponse createDynamics(DynamicsRequestDto dynamicsRequestDto, List<MultipartFile> files, String openId) {
        DynamicsEntity dynamicsEntity = dynamicsRequestDto.toDynamicsEntity();
        dynamicsEntity.setUserId(userRepository.getUserIdByOpenId(openId));
        dynamicsEntity.setCreateTs(new Timestamp(System.currentTimeMillis()));

        List<String> fileNames = new ArrayList<>();
        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            minio.upload(fileName, DY_BUCKET, file);
            fileNames.add(minioConfig.getEndpoint() + "/" + DY_BUCKET + "/" + fileName);
        });
        dynamicsEntity.setPicture(String.join(",", fileNames));
        dynamicsRepository.save(dynamicsEntity);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse getDynamics(Pageable pageable, String tag, String openId) {
        long userId = userRepository.getUserIdByOpenId(openId);

        StringBuffer sql = new StringBuffer("select dy.*, (select count(*) from dynamics_comment dc where dc.dy_id = dy.id), lk.likes as is_likes as count , (select count(*) from likes where lk.dy_id = dy.id) as likes from dynamics dy where dy.pass = 1 and lk.dy_id = dy.id and lk.user_id = :userId ");
        if (tag != null) {
            sql.append(" and dy.keyword like %" + tag + "%");
        }
        sql.append(" ORDER BY dy.create_ts");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());

        List<DynamicsSimpleEntity> dynamicsSimpleEntities = query.getResultList();

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("")
                .data(new PageImpl<>(dynamicsSimpleEntities, PageRequest.of(pageable.getPageNumber(), pageable.getPageNumber()), dynamicsSimpleEntities.size())).build();

    }

    @Override
    public CommonResponse likes(String openId, boolean likes, long id) {
        long userId = userRepository.getUserIdByOpenId(openId);
        LikesEntity likesEntity = likesRepository.getByUserIdAndProjectId(userId, id);
        if (likesEntity == null) {
            likesEntity = new LikesEntity();
            likesEntity.setUserId(userId);
            likesEntity.setDyId(id);
        }
        likesEntity.setLikes((byte) (likes ? 1 : 0));
        likesRepository.save(likesEntity);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse getComment(long id) {
        String sql = "WITH RECURSIVE comment_tree AS (" +
                "  SELECT" +
                "    id," +
                "    user_id," +
                "    content," +
                "    parent_id," +
                "    created_at," +
                "    updated_at," +
                "    is_deleted," +
                "    deleted_at," +
                "    is_approved," +
                "    dy_id," +
                "    1 AS level" +
                "  FROM dynamics_comment" +
                "  WHERE parent_id IS NULL AND is_deleted = FALSE AND is_approved = TRUE" +
                "  UNION ALL" +
                "  SELECT" +
                "    dynamics_comment.id," +
                "    dynamics_comment.user_id," +
                "    dynamics_comment.content," +
                "    dynamics_comment.parent_id," +
                "    dynamics_comment.created_at," +
                "    dynamics_comment.updated_at," +
                "    dynamics_comment.is_deleted," +
                "    dynamics_comment.deleted_at," +
                "    dynamics_comment.is_approved," +
                "    dynamics_comment.dy_id," +
                "    comment_tree.level + 1 AS level" +
                "  FROM dynamics_comment" +
                "  JOIN comment_tree ON dynamics_comment.parent_id = comment_tree.id" +
                "  WHERE dynamics_comment.is_deleted = FALSE AND dynamics_comment.is_approved = TRUE" +
                ")" +
                "SELECT" +
                "  id," +
                "  user_id," +
                "  content," +
                "  parent_id," +
                "  created_at," +
                "  updated_at," +
                "  is_deleted," +
                "  deleted_at," +
                "  is_approved," +
                "  dy_id," +
                "  level" +
                "FROM comment_tree where dy_id=:dyId" +
                "ORDER BY level, created_at";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("dyId", id);
        List<DyCommentSimple> dyCommentSimples = query.getResultList();

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .data(dyCommentSimples)
                .build();
    }

    @Override
    public CommonResponse createComment(String openId, DyCommentReqDto dyCommentReqDto) {
        UserEntity userEntity = userRepository.findByOpenid(openId);
        DynamicsCommentEntity dynamicsCommentEntity = dyCommentReqDto.toDynamicsCommentEntity();
        dynamicsCommentEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        dynamicsCommentEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        dynamicsCommentEntity.setUserId(userEntity.getId());
        dynamicsCommentEntity.setUserName(userEntity.getName());
        dynamicsCommentRepository.save(dynamicsCommentEntity);

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .build();
    }
}
