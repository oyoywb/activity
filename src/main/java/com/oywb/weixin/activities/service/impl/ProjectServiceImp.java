package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.dao.ProjectRepository;
import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ProjectRequestDto;
import com.oywb.weixin.activities.dto.response.ProjectResponseDto;
import com.oywb.weixin.activities.entity.ProjectEntity;
import com.oywb.weixin.activities.entity.ProjectSimpleEntity;
import com.oywb.weixin.activities.entity.ResumeDeliveryEntity;
import com.oywb.weixin.activities.entity.ResumeDeliveryRepository;
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

    public ProjectServiceImp(MinioConfig minioConfig, Minio minio, ProjectRepository projectRepository, EntityManager entityManager, ResumeDeliveryRepository resumeDeliveryRepository, UserRepository userRepository) {
        this.minioConfig = minioConfig;
        this.minio = minio;
        this.projectRepository = projectRepository;
        this.entityManager = entityManager;
        this.resumeDeliveryRepository = resumeDeliveryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommonResponse createProject(ProjectRequestDto projectRequestDto, List<MultipartFile> files, String openId) throws Exception {

        List<String> fileNameList = new ArrayList<>();
        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            minio.upload(fileName, PROJECT_BUCKET, file);
            fileNameList.add(minioConfig.getEndpoint() + "/" + PROJECT_BUCKET + "/" + fileName);
        });

        ProjectEntity projectEntity = projectRequestDto.toProjectEntity();
        projectEntity.setPicture(String.join(",", fileNameList));
        projectRepository.save(projectEntity);

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse getProjects(Pageable pageable) throws Exception {


        String sql = "select p.id,p.name,p.location,p.count,p.tag,p.end,(select count(*) from resume_delivery rd where rd.project_id = p.id and rd.pass = 1) as sign_count from project p ";
        Query query = entityManager.createNativeQuery(sql);
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
}
