package com.letty.lettylink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.letty.lettylink.admin.common.serialize.PhoneDesensitizationSerializer;
import lombok.Data;

import java.util.Date;

@Data
@JsonSerialize(using = PhoneDesensitizationSerializer.class)
public class UserRespDTO {

    private Long id;

    private String username;

    /**
     * users official name
     */
    private String realName;

    /**
     * phone
     */
    private String phone;

    /**
     * email
     */
    private String email;

    /**
     * time stamp deletion
     */
    private Long deletionTime;

    /**
     * created_time
     */
    private Date createdTime;

    /**
     * updated_time
     */
    private Date updatedTime;

    /**
     * deletion flag 0 or 1
     */
    private Integer delFlag;
}
