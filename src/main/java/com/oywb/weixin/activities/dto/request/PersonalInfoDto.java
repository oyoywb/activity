package com.oywb.weixin.activities.dto.request;

import lombok.Data;

@Data
public class PersonalInfoDto {

    private String school;

    private String name;

    //專業
    private String og;

    //年級
    private String subject;
}
