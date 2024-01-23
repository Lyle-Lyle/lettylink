package com.letty.lettylink.admin.dto.resp;

import lombok.Data;

@Data
public class UserLoginRespDTO {

    private String token;
    public UserLoginRespDTO(String token) {
        this.token = token;
    }
}
