package com.letty.lettylink.application.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.letty.lettylink.application.dao.entity.ShortLinkDO;
import com.letty.lettylink.application.dto.req.ShortLinkCreateReqDTO;
import com.letty.lettylink.application.dto.resp.ShortLinkCreateRespDTO;

public interface ShortLinkService extends IService<ShortLinkDO> {

    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}
