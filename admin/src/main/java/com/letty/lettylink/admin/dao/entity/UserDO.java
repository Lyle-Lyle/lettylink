package com.letty.lettylink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.letty.lettylink.admin.common.database.BaseDO;
import lombok.Data;

import java.util.Date;


@Data
@TableName("t_user")
public class UserDO extends BaseDO {

    private Long id;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;


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

}