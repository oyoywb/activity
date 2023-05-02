package com.oywb.weixin.activities.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.config.minio.MinioConfig;
import com.oywb.weixin.activities.dao.*;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ActivityRequestDto;
import com.oywb.weixin.activities.dto.request.InformationDetailRequestDto;
import com.oywb.weixin.activities.dto.response.ActivityResponseDto;
import com.oywb.weixin.activities.dto.response.ActivitySimpleDto;
import com.oywb.weixin.activities.entity.*;
import com.oywb.weixin.activities.service.ActivityService;
import com.oywb.weixin.activities.service.UserService;
import com.oywb.weixin.activities.util.CronUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    private final Minio minio;
    private final ActivityRepository activityRepository;
    private final InformationRepository informationRepository;
    private final InformationDetailRepository informationDetailRepository;
    private final EntityManager entityManager;
    private final UserService userService;
    private final PlanRepository planRepository;
    private final MinioConfig minioConfig;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final static String ACTIVITY_BUCKET = "activity";
    public ActivityServiceImpl(Minio minio, ActivityRepository activityRepository, InformationRepository informationRepository, InformationDetailRepository informationDetailRepository, EntityManager entityManager, UserService userService, PlanRepository planRepository, MinioConfig minioConfig) {
        this.minio = minio;
        this.activityRepository = activityRepository;
        this.informationRepository = informationRepository;
        this.informationDetailRepository = informationDetailRepository;
        this.entityManager = entityManager;
        this.userService = userService;
        this.planRepository = planRepository;
        this.minioConfig = minioConfig;
    }

    @Transactional
    public void createActivity(ActivityRequestDto activityRequestDto, List<MultipartFile> files, String openId) throws Exception {

        List<String> pictures = new ArrayList<>();
        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            pictures.add(minioConfig.getEndpoint() + "/" + ACTIVITY_BUCKET + "/" + fileName);
            minio.upload(fileName, ACTIVITY_BUCKET, file);
        });

        ActivityEntity activityEntity = activityRequestDto.toActivityEntity();
        activityEntity.setUserId(userService.getUserId(openId));
        activityEntity.setPicture(String.join(",", pictures));

        activityEntity = activityRepository.save(activityEntity);
        InformationEntity informationEntity = activityRequestDto.toInformationEntity();
        informationEntity.setActivityId(activityEntity.getId());

        informationRepository.save(informationEntity);
    }


    @Override
    public void updateActivity(ActivityRequestDto activityRequestDto, List<MultipartFile> files) {

        List<String> pictures = new ArrayList<>();
        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            pictures.add(minioConfig.getEndpoint() + "/" + ACTIVITY_BUCKET + "/" + fileName);
            minio.upload(fileName, ACTIVITY_BUCKET, file);
        });

        ActivityEntity activityEntity = activityRepository.findById(activityRequestDto.getId()).get();
        activityEntity.update(activityRequestDto);
        activityEntity.setVerified((byte) 0);
        activityEntity.setPicture(String.join(",", pictures));

        activityRepository.save(activityEntity);
        InformationEntity informationEntity = informationRepository.findByActivityId(activityRequestDto.getId());
        informationEntity.update(activityRequestDto.getInformationRequestDto());

        informationRepository.save(informationEntity);
    }

    @Transactional
    @Override
    public void verifiedActivity(List<Long> ids, byte verified) {
        activityRepository.verifiedActivity(ids, verified);
    }

    @Override
    public List<ActivitySimpleDto> getActivitiesSimple(String school, String campus, Timestamp start, Timestamp end, int flag, String openId, byte verified) throws Exception {
        long userId = userService.getUserId(openId);
        List<ActivitySimpleDto> activitySimpleDtoS = new ArrayList<>();

        String sql = "SELECT a.id as id , a.location as location, a.title as title, a.introduction as introduction, a.recommand as recommand, a.reaper as reaper, a.count as count, u.id AS user_id, u.profile as profile " +
                " FROM information_detail ind " +
                " RIGHT JOIN activity a ON a.id = ind.activity_id " +
                " LEFT JOIN user u ON ind.user_id = u.id " +
                " WHERE a.school = :school " +
                "  AND a.campus = :campus " +
                "  AND a.start BETWEEN :start AND :end "+
                "  AND a.verified = :verified ";

        if (flag == 1) {
            sql += " and a.user_id =:userId";
        }


        Query query = entityManager.createNativeQuery(sql, "ActivitySimpleEntity");

        if (flag == 1) {
            query.setParameter("userId", userId);
        }

        query.setParameter("school", school);
        query.setParameter("campus", campus);
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setParameter("verified", verified);

        List<ActivitySimpleEntity> activitySimpleEntities = query.getResultList();

        Map<Long, List<ActivitySimpleEntity>> map = activitySimpleEntities.stream().collect(Collectors.groupingBy(ActivitySimpleEntity::getId));

        map.forEach((k, v) -> {
            ActivitySimpleDto activitySimpleDto = new ActivitySimpleDto();

            List<ActivitySimpleDto.UserSimpleInfo> userSimpleInfos = new ArrayList<>();

            activitySimpleDto.setId(v.get(0).getId());
            activitySimpleDto.setLocation(v.get(0).getLocation());
            activitySimpleDto.setTitle(v.get(0).getTitle());
            activitySimpleDto.setContent(v.get(0).getIntroduction());
            activitySimpleDto.setReaper(v.get(0).getReaper());
            activitySimpleDto.setCount(v.get(0).getCount());
            activitySimpleDto.setUserSimpleInfos(userSimpleInfos);

            v.forEach(v1 -> {
                ActivitySimpleDto.UserSimpleInfo userSimpleInfo = activitySimpleDto.new UserSimpleInfo();
                userSimpleInfo.setId(v1.getUserId());
                userSimpleInfo.setProfile(v1.getProfile());
                userSimpleInfos.add(userSimpleInfo);
            });
            activitySimpleDtoS.add(activitySimpleDto);
        });

        return activitySimpleDtoS;

    }

    @Override
    public ActivityResponseDto getActivityDetail(long id) throws Exception {
        ActivityResponseDto activityResponseDto = new ActivityResponseDto();

        Optional<ActivityEntity> activityEntityOpt = activityRepository.findById(id);

        if (activityEntityOpt.isPresent()) {
            ActivityEntity activityEntity = activityEntityOpt.get();
            activityResponseDto = activityEntity.toActivityResponseDto();

            InformationEntity informationEntity = informationRepository.findByActivityId(activityEntity.getId());
            activityResponseDto.setInformationRequestDto(informationEntity.toInformationRequestDto());

            Long passCount = informationDetailRepository.countPassedByActivityId(activityEntity.getId());

            activityResponseDto.setPassCount(passCount == null ? 0 : passCount);
        }

        return activityResponseDto;

    }

    @Override
    public void signup(InformationDetailRequestDto informationDetailRequestDto, String openId) throws Exception {
        long userId = userService.getUserId(openId);
        informationDetailRequestDto.setUserId(userId);


        InformationDetailEntity informationDetailEntity = informationDetailRequestDto.toInformationDetailEntity();
        informationDetailEntity.setCustomQuestion(objectMapper.writeValueAsString(informationDetailRequestDto.getCustom_question()));

        informationDetailRepository.save(informationDetailEntity);
    }

    @Override
    public void addToPlan(long activityId, String openId) throws Exception {
        long userId = userService.getUserId(openId);
        Optional<ActivityEntity> activityEntityOpt = activityRepository.findById(activityId);
        if (activityEntityOpt.isPresent()) {
            ActivityEntity activityEntity = activityEntityOpt.get();

            PersonalPlanEntity personalPlanEntity = new PersonalPlanEntity();
            personalPlanEntity.setUserId(userId);
            personalPlanEntity.setName(activityEntity.getTitle());
            personalPlanEntity.setMode("activity");
            personalPlanEntity.setStart(activityEntity.getStart());
            personalPlanEntity.setEnd(activityEntity.getEnd());
            personalPlanEntity.setCron(CronUtils.timestampToCron(activityEntity.getStart()));
            planRepository.save(personalPlanEntity);
        }
    }

    @Override
    public List<ActivityEntity> getSelfActivity(String openId, byte flag) {
        long userId = userService.getUserId(openId);
        List<ActivityEntity> activityEntities = activityRepository.getSelfActivity(userId, flag);

        return activityEntities;
    }

    @Transactional
    @Override
    public void signDown(String openId, long activityId) {
        long userId = userService.getUserId(openId);
        informationDetailRepository.deleteByUserIdActivityId(activityId, userId);
    }

    @Override
    public List<InformationDetailEntity> getInformationDetails(long activityId, byte flag) {
        List<InformationDetailEntity> informationDetailEntities = informationDetailRepository.getInformationDetailEntitiesByActivityIdAndPassed(activityId, flag);

        return informationDetailEntities;
    }

    @Transactional
    @Override
    public CommonResponse activePass(List<Long> ids, long activityId) {
        Long passCount = informationDetailRepository.countPassedByActivityId(activityId);

        Optional<ActivityEntity> activityEntityOpt = activityRepository.findById(activityId);

        if (activityEntityOpt.isPresent()) {
            ActivityEntity activityEntity = activityEntityOpt.get();

            if (activityEntity.getCount() - (passCount == null ? 0 : passCount) < ids.size()) {
                return CommonResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("已通过人数超额")
                        .build();
            }
            informationDetailRepository.pass(ids);
            return CommonResponse.builder()
                    .code(HttpStatus.OK.value())
                    .build();
        } else {
            return CommonResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("活动不存在")
                    .build();
        }
    }

    @Override
    public List<ActivityEntity> getSelfSignActivity(String openId) {
        long userId = userService.getUserId(openId);
        List<ActivityEntity> activityEntities = activityRepository.getSelfSignActivity(userId);

        return activityEntities;
    }
}
