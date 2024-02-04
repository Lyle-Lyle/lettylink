package com.letty.lettylink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.letty.lettylink.admin.common.convention.result.Result;
import com.letty.lettylink.admin.dao.entity.GroupDO;
import com.letty.lettylink.admin.dao.entity.UserDO;
import com.letty.lettylink.admin.dto.req.GroupSaveReqDTO;
import com.letty.lettylink.admin.dto.req.GroupSortReqDTO;
import com.letty.lettylink.admin.dto.req.GroupUpdateDTO;
import com.letty.lettylink.admin.dto.resp.GroupRespDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GroupService extends IService<GroupDO> {

    /**
     * 新增短链接分组
     * @param groupName
     */
    void saveGroup(String groupName);


    // 查询用户短链接分组集合
    List<GroupRespDTO> listGroup();

    /**
     * 修改短链接分组
     */
    void updateGroup(GroupUpdateDTO requestParam);

    void deleteGroup(String gid);

    void sortGroup(List<GroupSortReqDTO> requestParam);

}
