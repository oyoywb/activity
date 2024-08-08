package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activity", schema = "oywb_test", catalog = "")
public class ActivityEntityNew extends ActivityEntity {
    private boolean isAddedToPlan;
}
