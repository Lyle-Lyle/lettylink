package com.letty.lettylink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.letty.lettylink.admin.dao.entity.UserDO;
import com.letty.lettylink.admin.dto.req.UserLoginReqDTO;
import com.letty.lettylink.admin.dto.req.UserRegisterReqDTO;
import com.letty.lettylink.admin.dto.req.UserUpdateReqDTO;
import com.letty.lettylink.admin.dto.resp.UserLoginRespDTO;
import com.letty.lettylink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {

    UserRespDTO getUserByUsername(String username);

    Boolean hasUsername(String username);

    void register(UserRegisterReqDTO requestParam);

    void update(UserUpdateReqDTO requestParam);

    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    Boolean checkLogin(String username, String token);

    void logout(String username, String token);
}
