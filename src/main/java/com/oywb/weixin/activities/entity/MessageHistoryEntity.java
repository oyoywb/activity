package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "message_history", schema = "oywb_test", catalog = "")
public class MessageHistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "sender")
    private Long sender;
    @Basic
    @Column(name = "receiver")
    private Long receiver;
    @Basic
    @Column(name = "context")
    private String context;
    @Basic
    @Column(name = "ts")
    private Timestamp ts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageHistoryEntity that = (MessageHistoryEntity) o;
        return id == that.id && Objects.equals(sender, that.sender) && Objects.equals(receiver, that.receiver) && Objects.equals(context, that.context) && Objects.equals(ts, that.ts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, receiver, context, ts);
    }
}
