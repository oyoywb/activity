package com.oywb.weixin.activities.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rolebinding")
public class RoleBinding {

    @Column(name = "openid")
    @Id
    private String openid;
    @Column(name = "user_role")
    private String userRole;


}
