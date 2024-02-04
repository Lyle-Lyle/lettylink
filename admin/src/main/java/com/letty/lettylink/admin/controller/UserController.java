package com.letty.lettylink.admin.controller;

import com.letty.lettylink.admin.common.convention.result.Result;
import com.letty.lettylink.admin.common.convention.result.Results;
import com.letty.lettylink.admin.common.enums.UserErrorCodeEnum;
import com.letty.lettylink.admin.dto.req.UserLoginReqDTO;
import com.letty.lettylink.admin.dto.req.UserRegisterReqDTO;
import com.letty.lettylink.admin.dto.req.UserUpdateReqDTO;
import com.letty.lettylink.admin.dto.resp.UserLoginRespDTO;
import com.letty.lettylink.admin.dto.resp.UserRespDTO;
import com.letty.lettylink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/letty-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        return Results.success(result);
//        if (result == null) {
//            return new Result<UserRespDTO>().setCode(UserErrorCodeEnum.USER_NULL.code()).setMessage(UserErrorCodeEnum.USER_NULL.message());
//        } else {
//            return new Result<UserRespDTO>(). setCode("0").setData(result);
//        }
    }

    @GetMapping("/api/letty-link/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    @PostMapping("/api/letty-link/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    @PutMapping("/api/letty-link/admin/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }


    @PostMapping("/api/letty-link/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }


    // 检查一下用户是否登录
    @GetMapping("/api/letty-link/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));
    }

    @DeleteMapping("/api/letty-link/admin/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token) {
        userService.logout(username, token);
        return Results.success();
    }

}
