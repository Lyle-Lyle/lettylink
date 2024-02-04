package com.letty.lettylink.admin.controller;


import com.letty.lettylink.admin.common.convention.result.Result;
import com.letty.lettylink.admin.common.convention.result.Results;
import com.letty.lettylink.admin.dto.req.GroupSaveReqDTO;
import com.letty.lettylink.admin.dto.req.GroupSortReqDTO;
import com.letty.lettylink.admin.dto.req.GroupUpdateDTO;
import com.letty.lettylink.admin.dto.resp.GroupRespDTO;
import com.letty.lettylink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/api/letty-link/admin/v1/group")
    public Result<Void> save(@RequestBody GroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * 查询短连接分组集合
     */
    @GetMapping("/api/letty-link/admin/v1/group")
    public Result<List<GroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    // 修改短链接分组名称
    @PutMapping("/api/letty-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody GroupUpdateDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    @DeleteMapping("/api/letty-link/admin/v1/group")
    public Result<Void> deleteGroup(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }


    /**
     * 短链接分组排序
     */
    @PostMapping("/api/letty-link/admin/v1/group/sort")
    public Result<Void> sortGroup(@RequestBody List<GroupSortReqDTO> requestParam) {
        groupService.sortGroup(requestParam);
        return Results.success();
    }

}
