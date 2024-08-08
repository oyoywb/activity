package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activity", schema = "oywb_test", catalog = "")
public class ActivityEntityNew extends ActivityEntity {

    @Column(name = "is_added_to_plan")
    private boolean isAddedToPlan;
}
