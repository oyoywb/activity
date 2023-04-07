package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "shop", schema = "oywb_test", catalog = "")
public class ShopEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "picture")
    private String picture;
    @Basic
    @Column(name = "start")
    private String start;
    @Basic
    @Column(name = "end")
    private String end;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "conditions")
    private String conditions;
    @Basic
    @Column(name = "pass")
    private int pass;
    @Basic
    @Column(name = "status")
    private int status;
    @Basic
    @Column(name = "create_ts")
    private Timestamp createTs;
    @Basic
    @Column(name = "update_ts")
    private Timestamp updateTs;
    @Basic
    @Column(name = "school")
    private String school;
    @Basic
    @Column(name = "zone")
    private String zone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Timestamp createTs) {
        this.createTs = createTs;
    }

    public Timestamp getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Timestamp updateTs) {
        this.updateTs = updateTs;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopEntity that = (ShopEntity) o;
        return id == that.id && userId == that.userId && pass == that.pass && status == that.status && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(picture, that.picture) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(location, that.location) && Objects.equals(conditions, that.conditions) && Objects.equals(createTs, that.createTs) && Objects.equals(updateTs, that.updateTs) && Objects.equals(school, that.school) && Objects.equals(zone, that.zone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, type, picture, start, end, location, conditions, pass, status, createTs, updateTs, school, zone);
    }
}
