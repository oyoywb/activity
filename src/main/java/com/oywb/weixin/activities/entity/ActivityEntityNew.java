package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
public class ActivityEntityNew extends ActivityEntity {

    @Column(name = "is_added_to_plan")
    private boolean isAddedToPlan;
}
