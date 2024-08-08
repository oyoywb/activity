package com.oywb.weixin.activities.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "is_added_to_plan", column = @Column(name = "is_added_to_plan")) })
public class ActivityEntityNew extends ActivityEntity {

    @Column(name = "is_added_to_plan")
    private boolean isAddedToPlan;
}
