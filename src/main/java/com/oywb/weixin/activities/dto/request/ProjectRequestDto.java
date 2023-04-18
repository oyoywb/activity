package com.oywb.weixin.activities.dto.request;

import com.oywb.weixin.activities.entity.ProjectEntity;
import lombok.Data;
import org.springframework.security.core.parameters.P;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ProjectRequestDto {
    private long id;
    //截止日期
    private Timestamp end;

    private String name;

    private String tag;

    private String organizer;

    private String location;

    private String introduction;

    //項目進度
    private String rop;

    private String period;

    private int count;

    //規模
    private int scope;

    private String expect;

    //目前情況
    private String currentSituation;


    public ProjectEntity toProjectEntity() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(this.name);
        projectEntity.setTag(this.tag);
        projectEntity.setOrganizer(this.organizer);
        projectEntity.setLocation(this.location);
        projectEntity.setIntroduction(this.introduction);
        projectEntity.setRop(this.rop);
        projectEntity.setCount(this.count);
        projectEntity.setExpect(this.expect);
        projectEntity.setScope(this.scope);
        projectEntity.setEnd(this.end);
        projectEntity.setPeriod(this.period);
        projectEntity.setCurrentSituation(this.currentSituation);

        return projectEntity;
    }
}
