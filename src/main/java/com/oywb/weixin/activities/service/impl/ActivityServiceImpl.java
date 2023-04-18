package com.oywb.weixin.activities.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oywb.weixin.activities.config.minio.Minio;
import com.oywb.weixin.activities.dao.*;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ActivityRequestDto;
import com.oywb.weixin.activities.dto.request.InformationDetailRequestDto;
import com.oywb.weixin.activities.dto.response.ActivityResponseDto;
import com.oywb.weixin.activities.dto.response.ActivitySimpleDto;
import com.oywb.weixin.activities.entity.*;
import com.oywb.weixin.activities.service.ActivityService;
import com.oywb.weixin.activities.util.CronUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final Minio minio;
    private final ActivityRepository activityRepository;
    private final InformationRepository informationRepository;
    private final InformationDetailRepository informationDetailRepository;
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final static String ACTIVITY_BUCKET = "activity";
    public ActivityServiceImpl(Minio minio, ActivityRepository activityRepository, InformationRepository informationRepository, InformationDetailRepository informationDetailRepository, EntityManager entityManager, UserRepository userRepository, PlanRepository planRepository) {
        this.minio = minio;
        this.activityRepository = activityRepository;
        this.informationRepository = informationRepository;
        this.informationDetailRepository = informationDetailRepository;
        this.entityManager = entityManager;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @Transactional
    public CommonResponse createActivity(ActivityRequestDto activityRequestDto, List<MultipartFile> files, String openId) throws Exception {

        files.forEach(file -> {
            String fileName = file.getOriginalFilename();
            minio.upload(fileName, ACTIVITY_BUCKET, file);
        });

        ActivityEntity activityEntity = activityRequestDto.toActivityEntity();
        activityEntity.setUserId(userRepository.getUserIdByOpenId(openId));

        activityEntity = activityRepository.save(activityEntity);
        InformationEntity informationEntity = activityRequestDto.toInformationEntity();
        informationEntity.setActivityId(activityEntity.getId());

        informationRepository.save(informationEntity);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse getActivitiesSimple(String school, String campus, Timestamp start, Timestamp end, int flag, String openId) throws Exception {
        long userId = userRepository.getUserIdByOpenId(openId);
        List<ActivitySimpleDto> activitySimpleDtoS = new ArrayList<>();

        String sql = "select a.id, a.location, a.title, a.introduction, a.reaper, a.count, u.id as user_id, u.profile from activity a, information_detail ind,user u WHERE a.school = :school " +
                "and a.campus = :campus and a.start BETWEEN :start and :end and a.id = ind.activity_id and ind.user_id = u.id";

        if (flag == 1) {
            sql += " and a.user_id =:userId";
        }

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("school", school);
        query.setParameter("campus", campus);
        query.setParameter("start", start);
        query.setParameter("end", end);
        query.setParameter("userId", userId);

        List<ActivitySimpleEntity> activitySimpleEntities = query.getResultList();

        Map<Long, List<ActivitySimpleEntity>> map = activitySimpleEntities.stream().collect(Collectors.groupingBy(ActivitySimpleEntity::getId));

        map.forEach((k, v) -> {
            ActivitySimpleDto activitySimpleDto = new ActivitySimpleDto();

            List<ActivitySimpleDto.UserSimpleInfo> userSimpleInfos = new ArrayList<>();

            activitySimpleDto.setId(v.get(0).getId());
            activitySimpleDto.setLocation(v.get(0).getLocation());
            activitySimpleDto.setTitle(v.get(0).getTitle());
            activitySimpleDto.setContent(v.get(0).getContent());
            activitySimpleDto.setReaper(v.get(0).getReaper());
            activitySimpleDto.setCount(v.get(0).getCount());

            v.forEach(v1 -> {
                ActivitySimpleDto.UserSimpleInfo userSimpleInfo = activitySimpleDto.new UserSimpleInfo();
                userSimpleInfo.setId(v1.getUserId());
                userSimpleInfo.setProfile(v1.getProfile());
                userSimpleInfos.add(userSimpleInfo);
            });
            activitySimpleDtoS.add(activitySimpleDto);
        });

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .data(activitySimpleDtoS)
                .build();

    }

    @Override
    public CommonResponse getActivityDetail(long id) throws Exception {
        ActivityResponseDto activityResponseDto = new ActivityResponseDto();

        Optional<ActivityEntity> activityEntityOpt = activityRepository.findById(id);

        if (activityEntityOpt.isPresent()) {
            ActivityEntity activityEntity = activityEntityOpt.get();
            activityResponseDto = activityEntity.toActivityResponseDto();

            InformationEntity informationEntity = informationRepository.findByActivityId(activityEntity.getId());
            activityResponseDto.setInformationRequestDto(informationEntity.toInformationRequestDto());

            activityResponseDto.setPassCount(informationDetailRepository.countPassedByActivityId(activityEntity.getId()));
        }

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .data(activityResponseDto)
                .build();

    }

    @Override
    public CommonResponse signup(InformationDetailRequestDto informationDetailRequestDto, Authentication authentication) throws Exception {
        long userId = userRepository.getUserIdByOpenId(authentication.getName());
        informationDetailRequestDto.setUserId(userId);


        InformationDetailEntity informationDetailEntity = informationDetailRequestDto.toInformationDetailEntity();
        informationDetailEntity.setCustomQuestion(objectMapper.writeValueAsString(informationDetailRequestDto.getCustom_question()));

        informationDetailRepository.save(informationDetailEntity);

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse addToPlan(long activityId, String openId) throws Exception {
        long userId = userRepository.getUserIdByOpenId(openId);
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


        return CommonResponse.builder().code(HttpStatus.OK.value())
                .build();

    }

    @Override
    public CommonResponse getSelfActivity(String openId, byte flag) {
        long userId = userRepository.getUserIdByOpenId(openId);
        List<ActivityEntity> activityEntities = activityRepository.getSelfActivity(userId, flag);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .data(activityEntities)
                .build();
    }

    @Override
    public CommonResponse signDown(String openId, long activityId) {
        long userId = userRepository.getUserIdByOpenId(openId);
        informationDetailRepository.deleteByUserIdActivityId(activityId, userId);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse getInformationDetails(long activityId, byte flag) {
        List<InformationDetailEntity> informationDetailEntities = informationDetailRepository.getInformationDetailEntitiesByActivityIdAndPassed(activityId, flag);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .data(informationDetailEntities)
                .build();
    }

    @Override
    public CommonResponse activePass(List<Long> ids, long activityId) {
        long passCount = informationDetailRepository.countPassedByActivityId(activityId);

        Optional<ActivityEntity> activityEntityOpt = activityRepository.findById(activityId);

        if (activityEntityOpt.isPresent()) {
            ActivityEntity activityEntity = activityEntityOpt.get();

            if (activityEntity.getCount() - passCount < ids.size()) {
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
    public CommonResponse getSelfSignActivity(String openId) {
        long userId = userRepository.getUserIdByOpenId(openId);
        List<ActivityEntity> activityEntities = activityRepository.getSelfSignActivity(userId);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .data(activityEntities)
                .build();
    }
}
