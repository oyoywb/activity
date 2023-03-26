package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.awt.print.PrinterGraphics;

@Entity
@Table(name = "user")
public class User {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "openid")
    private String openid;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "scp")
    private Byte[] scp;
    @Column(name = "profile")
    private Byte[] profile;
    @Column(name = "registed")
    private Boolean registed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte[] getScp() {
        return scp;
    }

    public void setScp(Byte[] scp) {
        this.scp = scp;
    }

    public Byte[] getProfile() {
        return profile;
    }

    public void setProfile(Byte[] profile) {
        this.profile = profile;
    }

    public Boolean getRegisted() {
        return registed;
    }

    public void setRegisted(Boolean registed) {
        this.registed = registed;
    }
}
