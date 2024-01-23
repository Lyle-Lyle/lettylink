package com.letty.lettylink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("t_user")
public class UserDO {

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

    /**
     * created_time
     */
    @TableField(fill= FieldFill.INSERT)
    private Date createdTime;

    /**
     * updated_time
     */
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    /**
     * deletion flag 0 or 1
     */
    @TableField(fill= FieldFill.INSERT)
    private Integer delFlag;

}