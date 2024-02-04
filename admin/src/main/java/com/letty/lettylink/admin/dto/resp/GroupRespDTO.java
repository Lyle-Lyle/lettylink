package com.letty.lettylink.admin.dto.resp;

import lombok.Data;

@Data
public class GroupRespDTO {
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户名
     */
    private String username;

    private Integer sortOrder;


}
