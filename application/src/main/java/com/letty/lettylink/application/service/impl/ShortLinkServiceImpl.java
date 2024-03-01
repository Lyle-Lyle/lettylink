package com.letty.lettylink.application.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letty.lettylink.application.dao.entity.ShortLinkDO;
import com.letty.lettylink.application.dao.mapper.ShortLinkMapper;
import com.letty.lettylink.application.dto.req.ShortLinkCreateReqDTO;
import com.letty.lettylink.application.dto.resp.ShortLinkCreateRespDTO;
import com.letty.lettylink.application.service.ShortLinkService;
import com.letty.lettylink.application.toolkit.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
    /**
     * 创建短链接：suffix +
     */
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String suffix = generateSuffix(requestParam);
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setShortUri(suffix);
        shortLinkDO.setEnableStatus(0);
        shortLinkDO.setFullShortUrl(requestParam.getDomain() + "/" + suffix);
        baseMapper.insert(shortLinkDO);
        return ShortLinkCreateRespDTO.builder()
                .fullShortURL(shortLinkDO.getFullShortUrl())
                .originURL(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        String originURL = requestParam.getOriginUrl();
        return HashUtil.hashToBase62(originURL);
    }
}
