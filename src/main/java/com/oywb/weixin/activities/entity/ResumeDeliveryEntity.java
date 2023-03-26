package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resume_delivery", schema = "oywb_test", catalog = "")
public class ResumeDeliveryEntity {
    private long id;
    private byte pass;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pass", nullable = false)
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
        return id == that.id &&
                pass == that.pass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pass);
    }
}
