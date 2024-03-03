package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class ActivityEntityNew {
    private ActivityEntity activity;
    private boolean isAddedToPlan;
}
