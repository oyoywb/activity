package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.dao.ProjectRepository;
import com.oywb.weixin.activities.dao.ResumeDeliveryRepository;
import com.oywb.weixin.activities.dao.ResumeRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ProjectRequestDto;
import com.oywb.weixin.activities.dto.response.ProjectResponseDto;
import com.oywb.weixin.activities.entity.*;
import com.oywb.weixin.activities.service.ProjectService;
import com.oywb.weixin.activities.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
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
    private final ResumeRepository resumeRepository;
    private final UserService userService;

    public ProjectServiceImp(MinioConfig minioConfig, Minio minio, ProjectRepository projectRepository, EntityManager entityManager, ResumeDeliveryRepository resumeDeliveryRepository, ResumeRepository resumeRepository, UserService userService) {
        this.minioConfig = minioConfig;
        this.minio = minio;
        this.projectRepository = projectRepository;
        this.entityManager = entityManager;
        this.resumeDeliveryRepository = resumeDeliveryRepository;
        this.resumeRepository = resumeRepository;
        this.userService = userService;
    }

    @Override
    public void createProject(ProjectRequestDto projectRequestDto, StandardMultipartHttpServletRequest req, String openId) throws Exception {
        long userId = userService.getUserId(openId);

        List<String> fileNameList = new ArrayList<>();

        Iterator<String> iterator = req.getFileNames();
        while (iterator.hasNext()) {
            MultipartFile file = req.getFile(iterator.next());
            String fileName = file.getOriginalFilename();
            minio.upload(fileName, PROJECT_BUCKET, file);
            fileNameList.add(minioConfig.getEndpoint() + "/" + PROJECT_BUCKET + "/" + fileName);
        }
/*        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            minio.upload(fileName, PROJECT_BUCKET, file);
            fileNameList.add(minioConfig.getEndpoint() + "/" + PROJECT_BUCKET + "/" + fileName);
        });*/

        ProjectEntity projectEntity = projectRequestDto.toProjectEntity();
        projectEntity.setPicture(String.join(",", fileNameList));
        projectEntity.setUserId(userId);
        projectRepository.save(projectEntity);
    }

    @Override
    public void updateProject(ProjectRequestDto projectRequestDto, List<MultipartFile> files) {

        List<String> fileNameList = new ArrayList<>();
        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            minio.upload(fileName, PROJECT_BUCKET, file);
            fileNameList.add(minioConfig.getEndpoint() + "/" + PROJECT_BUCKET + "/" + fileName);
        });

        ProjectEntity projectEntity = projectRepository.findById(projectRequestDto.getId()).get();
        projectEntity.update(projectRequestDto);
        projectEntity.setPicture(String.join(",", fileNameList));
        projectRepository.save(projectEntity);
    }

    @Transactional
    @Override
    public void passProject(List<Long> ids, byte pass) {
        projectRepository.passProject(ids, pass);
    }

    @Override
    public Page<ProjectSimpleEntity> getProjects(Pageable pageable, int flag, String openId, byte pass, String name) throws Exception {
        long userId = userService.getUserId(openId);

        String sql = "select p.id,p.name,p.location,p.count,p.tag,p.end,(select count(*) from resume_delivery rd where rd.project_id = p.id and rd.pass = 1) as sign_count from project p where p.pass = :pass ";

        if (flag == 1) {
            sql += " and p.user_id = :userId ";
        }

        if (name == null && name != "") {
            sql += " and p.name like '%:" + name + "%'";
        }

        Query query = entityManager.createNativeQuery(sql);
        if (flag == 1) {
            query.setParameter("userId", userId);
        }

        query.setParameter("pass", pass);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());

        List<ProjectSimpleEntity> projectSimpleEntities = query.getResultList();

        return new PageImpl<>(projectSimpleEntities, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), projectSimpleEntities.size());

    }

    @Override
    public ProjectResponseDto getProjectDetail(Long id) throws Exception {
        Optional<ProjectEntity> projectEntityOpt = projectRepository.findById(id);
        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        if(projectEntityOpt.isPresent()) {
            ProjectEntity projectEntity = projectEntityOpt.get();
            projectResponseDto = projectEntity.toProjectResponseDto();
            Integer passCount = resumeDeliveryRepository.countByProjectId(id);
            projectResponseDto.setPassCount(passCount == null ? 0 : passCount);
        }

        return projectResponseDto;
    }

    @Override
    public void signup(long projectId, String openId) {
        ResumeDeliveryEntity deliveryEntity = new ResumeDeliveryEntity();
        deliveryEntity.setProjectId(projectId);
        deliveryEntity.setUserId(userService.getUserId(openId));
        resumeDeliveryRepository.save(deliveryEntity);
    }

    @Override
    public List<ProjectEntity> getSelfProject(String openId, byte passed) {
        long userId = userService.getUserId(openId);
        List<ProjectEntity> projectEntities = projectRepository.getSelfProject(userId, passed);

        return projectEntities;
    }

    @Transactional
    @Override
    public void signDown(String openId, long projectId) {
        long userId = userService.getUserId(openId);
        resumeDeliveryRepository.deleteByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public List<ResumeEntity> getResumes(long projectId, byte pass) {
        List<ResumeEntity> resumeEntities = resumeRepository.getResumeByProjectIdAndPass(projectId, pass);

        return resumeEntities;
    }

    @Transactional
    @Override
    public CommonResponse resumePass(List<Long> userIds, long projectId, byte flag) {

        Optional<ProjectEntity> projectEntityOpt = projectRepository.findById(projectId);

        if (projectEntityOpt.isPresent()) {
            if (flag == 1) {
                ProjectEntity projectEntity = projectEntityOpt.get();
                Integer passCount = resumeDeliveryRepository.countByProjectId(projectId);

                if (projectEntity.getCount() - (passCount == null ? 0 : passCount) < userIds.size()) {
                    return CommonResponse.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .message("报名人数超过配置")
                            .build();
                }
            }
            resumeDeliveryRepository.updateByProjectIdAndUserId(projectId, userIds, flag);
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
    public List<ProjectEntity> getSelfSignProject(String openId) {
        long userId = userService.getUserId(openId);
        List<ProjectEntity> projectEntities = projectRepository.getSelfSignProject(userId);

        return projectEntities;
    }
}
