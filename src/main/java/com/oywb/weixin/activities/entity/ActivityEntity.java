package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "activity", schema = "oywb_test", catalog = "")
public class ActivityEntity {
    private long id;
    private String title;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private Integer count;
    private String location;
    private String organizer;
    private String introduction;
    private String rule;
    private String form;
    private Byte collectOrNot;
    private String recommand;
    private String reaper;
    private Byte verified;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "start", nullable = true)
    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end", nullable = true)
    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    @Basic
    @Column(name = "count", nullable = true)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 255)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "organizer", nullable = true, length = 255)
    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    @Basic
    @Column(name = "introduction", nullable = true, length = -1)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "rule", nullable = true, length = -1)
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Basic
    @Column(name = "form", nullable = true, length = -1)
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Basic
    @Column(name = "collect_or_not", nullable = true)
    public Byte getCollectOrNot() {
        return collectOrNot;
    }

    public void setCollectOrNot(Byte collectOrNot) {
        this.collectOrNot = collectOrNot;
    }

    @Basic
    @Column(name = "recommand", nullable = true, length = -1)
    public String getRecommand() {
        return recommand;
    }

    public void setRecommand(String recommand) {
        this.recommand = recommand;
    }

    @Basic
    @Column(name = "reaper", nullable = true, length = -1)
    public String getReaper() {
        return reaper;
    }

    public void setReaper(String reaper) {
        this.reaper = reaper;
    }

    @Basic
    @Column(name = "verified", nullable = true)
    public Byte getVerified() {
        return verified;
    }

    public void setVerified(Byte verified) {
        this.verified = verified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(type, that.type) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(count, that.count) &&
                Objects.equals(location, that.location) &&
                Objects.equals(organizer, that.organizer) &&
                Objects.equals(introduction, that.introduction) &&
                Objects.equals(rule, that.rule) &&
                Objects.equals(form, that.form) &&
                Objects.equals(collectOrNot, that.collectOrNot) &&
                Objects.equals(recommand, that.recommand) &&
                Objects.equals(reaper, that.reaper) &&
                Objects.equals(verified, that.verified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, start, end, count, location, organizer, introduction, rule, form, collectOrNot, recommand, reaper, verified);
    }
}
