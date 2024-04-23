package com.letty.lettylink.application.common.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * 数据库持久层对象基础属性
 */

@Data
public class BaseDO {

    @TableField(fill= FieldFill.INSERT)
    private Date createTime;

    /**
     * updated_time
     */
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * deletion flag 0 or 1
     */
    @TableField(fill= FieldFill.INSERT)
    private Integer delFlag;
}
