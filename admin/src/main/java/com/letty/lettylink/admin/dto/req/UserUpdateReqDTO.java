package com.letty.lettylink.admin.dto.req;

import lombok.Data;

@Data
public class UserUpdateReqDTO {


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

}
