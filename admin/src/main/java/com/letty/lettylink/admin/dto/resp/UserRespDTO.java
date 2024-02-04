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
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 状态 0 - 正常
     */
    private Integer userStatus;

    /**
     * time stamp deletion
     */
    private Long deletionTime;

    /**
     * 用户角色 0 - 普通用户 1 - 管理员
     */
    private Integer userRole;

    /**
     * 标签 json 列表
     */
    private String tags;

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
