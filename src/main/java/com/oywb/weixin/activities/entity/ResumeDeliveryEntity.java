package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resume_delivery", schema = "oywb_test", catalog = "")
public class ResumeDeliveryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "project_id")
    private long projectId;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "pass")
    private byte pass;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public byte getPass() {
        return pass;
    }

    public void setPass(byte pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResumeDeliveryEntity that = (ResumeDeliveryEntity) o;
        return id == that.id && projectId == that.projectId && userId == that.userId && pass == that.pass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, userId, pass);
    }
}
