package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.dao.ProjectRepository;
import com.oywb.weixin.activities.dao.ResumeRepository;
import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ProjectRequestDto;
import com.oywb.weixin.activities.dto.response.ProjectResponseDto;
import com.oywb.weixin.activities.entity.*;
import com.oywb.weixin.activities.service.ProjectService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService {
    private final MinioConfig minioConfig;
    private final Minio minio;
    private final ProjectRepository projectRepository;
    private final static String PROJECT_BUCKET = "project";
    private final EntityManager entityManager;
    private final ResumeDeliveryRepository resumeDeliveryRepository;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;

    public ProjectServiceImp(MinioConfig minioConfig, Minio minio, ProjectRepository projectRepository, EntityManager entityManager, ResumeDeliveryRepository resumeDeliveryRepository, UserRepository userRepository, ResumeRepository resumeRepository) {
        this.minioConfig = minioConfig;
        this.minio = minio;
        this.projectRepository = projectRepository;
        this.entityManager = entityManager;
        this.resumeDeliveryRepository = resumeDeliveryRepository;
        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
    }

    @Override
    public CommonResponse createProject(ProjectRequestDto projectRequestDto, List<MultipartFile> files, String openId) throws Exception {
        long userId = userRepository.getUserIdByOpenId(openId);

        List<String> fileNameList = new ArrayList<>();
        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            minio.upload(fileName, PROJECT_BUCKET, file);
            fileNameList.add(minioConfig.getEndpoint() + "/" + PROJECT_BUCKET + "/" + fileName);
        });

        ProjectEntity projectEntity = projectRequestDto.toProjectEntity();
        projectEntity.setPicture(String.join(",", fileNameList));
        projectEntity.setUserId(userId);
        projectRepository.save(projectEntity);

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse getProjects(Pageable pageable, int flag, String openId) throws Exception {
        long userId = userRepository.getUserIdByOpenId(openId);

        String sql = "select p.id,p.name,p.location,p.count,p.tag,p.end,(select count(*) from resume_delivery rd where rd.project_id = p.id and rd.pass = 1) as sign_count from project p ";
        if (flag == 1) {
            sql += " where p.user_id = :userId";
        }
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId", userId);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());

        List<ProjectSimpleEntity> projectSimpleEntities = query.getResultList();

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .data(new PageImpl<>(projectSimpleEntities, PageRequest.of(pageable.getPageNumber(), pageable.getPageNumber()), projectSimpleEntities.size()))
                .build();

    }

    @Override
    public CommonResponse getProjectDetail(Long id) throws Exception {
        Optional<ProjectEntity> projectEntityOpt = projectRepository.findById(id);
        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        if(projectEntityOpt.isPresent()) {
            ProjectEntity projectEntity = projectEntityOpt.get();
            projectResponseDto = projectEntity.toProjectResponseDto();
            projectResponseDto.setPassCount(resumeDeliveryRepository.getCountByProjectId(id));
        }

        return CommonResponse.builder().data(projectResponseDto)
                .build();
    }

    @Override
    public CommonResponse signup(long projectId, String openId) {
        ResumeDeliveryEntity deliveryEntity = new ResumeDeliveryEntity();
        deliveryEntity.setProjectId(projectId);
        deliveryEntity.setUserId(userRepository.getUserIdByOpenId(openId));
        resumeDeliveryRepository.save(deliveryEntity);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse getSelfProject(String openId, byte passed) {
        long userId = userRepository.getUserIdByOpenId(openId);
        List<ProjectEntity> projectEntities = projectRepository.getSelfProject(userId, passed);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .data(projectEntities)
                .build();
    }

    @Override
    public CommonResponse signDown(String openId, long projectId) {
        long userId = userRepository.getUserIdByOpenId(openId);
        resumeDeliveryRepository.deleteByUserIdAndProjectId(userId, projectId);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse getResumes(long projectId, byte pass) {
        List<ResumeEntity> resumeEntities = resumeRepository.getResumeByProjectIdAndPass(projectId, pass);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .data(resumeEntities)
                .build();
    }

    @Override
    public CommonResponse resumePass(List<Long> userIds, long projectId) {

        Optional<ProjectEntity> projectEntityOpt = projectRepository.findById(projectId);

        if (projectEntityOpt.isPresent()) {
            ProjectEntity projectEntity = projectEntityOpt.get();
            int passCount = resumeDeliveryRepository.getCountByProjectId(projectId);

            if (projectEntity.getCount() - passCount < userIds.size()) {
                return CommonResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("报名人数超过配置")
                        .build();
            }

            resumeDeliveryRepository.updateByProjectIdAndUserId(projectId, userIds);
        } else {
            return CommonResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("项目不存在")
                    .build();
        }
        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse getSelfSignProject(String openId) {
        long userId = userRepository.getUserIdByOpenId(openId);
        List<ProjectEntity> projectEntities = projectRepository.getSelfSignProject(userId);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .data(projectEntities)
                .build();
    }
}
