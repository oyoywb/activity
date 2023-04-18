package com.oywb.weixin.activities.entity;

import com.oywb.weixin.activities.dto.request.ProjectRequestDto;
import com.oywb.weixin.activities.dto.response.ProjectResponseDto;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "project")
@DynamicUpdate
@DynamicInsert
public class ProjectEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "tag")
    private String tag;
    @Basic
    @Column(name = "organizer")
    private String organizer;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "introduction")
    private String introduction;
    @Basic
    @Column(name = "rop")
    private String rop;
    @Basic
    @Column(name = "count")
    private int count;
    @Basic
    @Column(name = "expect")
    private String expect;
    @Basic
    @Column(name = "scope")
    private int scope;
    @Basic
    @Column(name = "picture")
    private String picture;
    @Basic
    @Column(name = "end")
    private Timestamp end;
    @Basic
    @Column(name = "period")
    private String period;
    @Basic
    @Column(name = "current_situation")
    private String currentSituation;
    @Basic
    @Column(name = "pass")
    private Byte pass;

    @Basic
    @Column(name = "user_id")
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRop() {
        return rop;
    }

    public void setRop(String rop) {
        this.rop = rop;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCurrentSituation() {
        return currentSituation;
    }

    public void setCurrentSituation(String currentSituation) {
        this.currentSituation = currentSituation;
    }

    public Byte getPass() {
        return pass;
    }

    public void setPass(Byte pass) {
        this.pass = pass;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return id == that.id && count == that.count && scope == that.scope && Objects.equals(name, that.name) && Objects.equals(tag, that.tag) && Objects.equals(organizer, that.organizer) && Objects.equals(location, that.location) && Objects.equals(introduction, that.introduction) && Objects.equals(rop, that.rop) && Objects.equals(expect, that.expect) && Objects.equals(picture, that.picture) && Objects.equals(end, that.end) && Objects.equals(period, that.period) && Objects.equals(currentSituation, that.currentSituation) && Objects.equals(pass, that.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tag, organizer, location, introduction, rop, count, expect, scope, picture, end, period, currentSituation, pass);
    }

    public ProjectResponseDto toProjectResponseDto() {
        ProjectResponseDto projectResponseDto = new ProjectResponseDto();
        projectResponseDto.setId(this.id);
        projectResponseDto.setEnd(this.end);
        projectResponseDto.setTag(this.tag);
        projectResponseDto.setOrganizer(this.organizer);
        projectResponseDto.setLocation(this.location);
        projectResponseDto.setIntroduction(this.introduction);
        projectResponseDto.setRop(this.rop);
        projectResponseDto.setPeriod(this.period);
        projectResponseDto.setCount(this.count);
        projectResponseDto.setScope(this.scope);
        projectResponseDto.setExpect(this.expect);
        projectResponseDto.setPicture(Arrays.asList(this.picture.split(",")));
        projectResponseDto.setCurrentSituation(this.currentSituation);
        projectResponseDto.setPass(this.pass == 1);

        return projectResponseDto;

    }

    public void update(ProjectRequestDto projectRequestDto) {
        this.name = projectRequestDto.getName();
        this.tag = projectRequestDto.getTag();
        this.organizer = projectRequestDto.getOrganizer();
        this.location = projectRequestDto.getLocation();
        this.introduction = projectRequestDto.getIntroduction();
        this.rop = projectRequestDto.getRop();
        this.count = projectRequestDto.getCount();
        this.expect = projectRequestDto.getExpect();
        this.scope = projectRequestDto.getScope();
        this.end = projectRequestDto.getEnd();
        this.period = projectRequestDto.getPeriod();
        this.currentSituation = projectRequestDto.getCurrentSituation();
    }
}
