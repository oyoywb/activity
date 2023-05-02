package com.oywb.weixin.activities.service.impl;

import com.oywb.weixin.activities.dao.PlanRepository;
import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PlanRequestDto;
import com.oywb.weixin.activities.dto.response.PlanResponseDto;
import com.oywb.weixin.activities.entity.PersonalPlanEntity;
import com.oywb.weixin.activities.service.PlanService;
import com.oywb.weixin.activities.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlanServiceImpl implements PlanService {

    private final static long THREE_DAY_AFTER = 3 * 24 * 60 * 60 * 1000;

    private final PlanRepository planRepository;
    private final UserService userService;

    public PlanServiceImpl(PlanRepository planRepository, UserService userService) {
        this.planRepository = planRepository;
        this.userService = userService;
    }

    @Override
    public void createPlan(PlanRequestDto planRequestDto, String openId) throws Exception {
        PersonalPlanEntity personalPlanEntity = planRequestDto.toPersonalPlanEntity();
        personalPlanEntity.setUserId(userService.getUserId(openId));

        planRepository.save(personalPlanEntity);
    }

    @Override
    public List<PlanResponseDto> getPlan(String openId) throws Exception {
        List<PersonalPlanEntity> personalPlanEntityList = planRepository.findPersonalPlanEntitiesByUserId(userService.getUserId(openId));

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

        return planResponseDtoList;
    }

    @Override
    public void updatePlan(PlanRequestDto planRequestDto, String openId) throws Exception {
        long userId = userService.getUserId(openId);
        PersonalPlanEntity personalPlanEntity = planRepository.findPersonalPlanEntityByIdAndUserId(planRequestDto.getId(), userId);

        if (personalPlanEntity != null) {
            planRequestDto.updatePersonalPlanEntity(personalPlanEntity);
            planRepository.save(personalPlanEntity);
        }
    }

    @Override
    public void deletePlan(long id) throws Exception {
        planRepository.deleteById(id);
    }

    @Override
    public void topPlan(long id, boolean isTop) throws Exception {
        Optional<PersonalPlanEntity> personalPlanEntity = planRepository.findById(id);

        if (personalPlanEntity.isPresent())  {
            personalPlanEntity.get().setIsTop((byte) (isTop ? 1 : 0));
            planRepository.save(personalPlanEntity.get());
        }
    }

}
