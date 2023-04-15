package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.dao.PlanRepository;
import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PlanRequestDto;
import com.oywb.weixin.activities.dto.response.PlanResponseDto;
import com.oywb.weixin.activities.entity.PersonalPlanEntity;
import com.oywb.weixin.activities.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PlanServiceImpl implements PlanService {

    private final static long THREE_DAY_AFTER = 3 * 24 * 60 * 60 * 1000;

    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public PlanServiceImpl(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommonResponse createPlan(PlanRequestDto planRequestDto, String openId) throws Exception {
        PersonalPlanEntity personalPlanEntity = planRequestDto.toPersonalPlanEntity();
        personalPlanEntity.setUserId(userRepository.getUserIdByOpenId(openId));

        planRepository.save(personalPlanEntity);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .message("")
                .build();

    }

    @Override
    public CommonResponse getPlan(String openId) throws Exception {
        List<PersonalPlanEntity> personalPlanEntityList = planRepository.findPersonalPlanEntitiesByUserId(userRepository.getUserIdByOpenId(openId));

        List<PlanResponseDto> planResponseDtoList = new ArrayList<>();

        Date now = new Date(System.currentTimeMillis());

        personalPlanEntityList.forEach(personalPlanEntity -> {
            boolean isValid = CronExpression.isValidExpression(personalPlanEntity.getCron());

            if (isValid) {
                try {
                    CronExpression cron = new CronExpression(personalPlanEntity.getCron());
                    Date next = cron.getNextValidTimeAfter(now);

                    if (next.getTime() - now.getTime() < THREE_DAY_AFTER) {
                        PlanResponseDto planResponseDto = personalPlanEntity.toPlanResponseDto();
                        planResponseDto.setTs(new Timestamp(next.getTime()));
                        planResponseDtoList.add(planResponseDto);
                    }
                } catch (Exception e) {
                    log.error("get plan fail", e);
                }
            }
        });

        return CommonResponse.builder().code(HttpStatus.OK.value())
                .message("")
                .data(planResponseDtoList)
                .build();
    }

    @Override
    public CommonResponse updatePlan(PlanRequestDto planRequestDto) throws Exception {

        PersonalPlanEntity personalPlanEntity = planRequestDto.toPersonalPlanEntity();

        planRepository.save(personalPlanEntity);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .message("")
                .build();
    }

    @Override
    public CommonResponse deletePlan(long id, long userId) throws Exception {

        planRepository.deleteById(id);

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @Override
    public CommonResponse topPlan(long id, long userId, boolean isTop) throws Exception {
        PersonalPlanEntity personalPlanEntity = planRepository.findPersonalPlanEntityByIdAndUserId(id, userId);

        if (personalPlanEntity != null) {
            personalPlanEntity.setIsTop((byte) (isTop ? 1 : 0));
            planRepository.save(personalPlanEntity);
        }

        return CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .build();
    }

}
