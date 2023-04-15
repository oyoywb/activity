package com.oywb.weixin.activities.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "dynamics_comment")
@DynamicInsert
@DynamicUpdate
public class DynamicsCommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "parent_id")
    private Long parentId;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Basic
    @Column(name = "is_deleted")
    private byte isDeleted;
    @Basic
    @Column(name = "deleted_at")
    private Timestamp deletedAt;
    @Basic
    @Column(name = "is_approved")
    private byte isApproved;
    @Basic
    @Column(name = "dy_id")
    private long dyId;

    @Basic
    @Column(name = "user_name")
    private String userName;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public byte getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(byte isApproved) {
        this.isApproved = isApproved;
    }

    public long getDyId() {
        return dyId;
    }

    public void setDyId(long dyId) {
        this.dyId = dyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicsCommentEntity that = (DynamicsCommentEntity) o;
        return id == that.id && userId == that.userId && isDeleted == that.isDeleted && isApproved == that.isApproved && dyId == that.dyId && Objects.equals(content, that.content) && Objects.equals(parentId, that.parentId) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(deletedAt, that.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, content, parentId, createdAt, updatedAt, isDeleted, deletedAt, isApproved, dyId);
    }
}
