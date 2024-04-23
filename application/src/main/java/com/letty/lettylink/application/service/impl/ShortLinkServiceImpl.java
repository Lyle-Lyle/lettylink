package com.letty.lettylink.application.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letty.lettylink.application.common.convention.exception.ServiceException;
import com.letty.lettylink.application.config.RBloomFilterConfiguration;
import com.letty.lettylink.application.dao.entity.ShortLinkDO;
import com.letty.lettylink.application.dao.mapper.ShortLinkMapper;
import com.letty.lettylink.application.dto.req.ShortLinkCreateReqDTO;
import com.letty.lettylink.application.dto.resp.ShortLinkCreateRespDTO;
import com.letty.lettylink.application.service.ShortLinkService;
import com.letty.lettylink.application.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;



    /**
     * 创建短链接：suffix +
     */
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String suffix = generateSuffix(requestParam);
        String fullShortUrl = requestParam.getDomain() + "/" + suffix;
        // 不用字符串拼接还可以怎么写
//        String fullShortUrl = StrBuilder.create(requestParam.getDo main())
//                .append("/")
//                .append(suffix)
//                .toString();
//        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
//        shortLinkDO.setShortUri(suffix);
//        shortLinkDO.setEnableStatus(0);
//        shortLinkDO.setFullShortUrl(requestParam.getDomain() + "/" + suffix);
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .domain(requestParam.getDomain())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(requestParam.getValidDate())
                .describe(requestParam.getDescribe())
                .shortUri(suffix)
                .enableStatus(0)
                .fullShortUrl(fullShortUrl)
                .build();
        try {
            baseMapper.insert(shortLinkDO);
        } catch (DuplicateKeyException ex) {
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class).eq(ShortLinkDO::getFullShortUrl, fullShortUrl);
            ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
            if (hasShortLinkDO != null) {
                log.warn("短链接：{} 重复入库", fullShortUrl);
                throw new ServiceException("短链接生成重复");
            }

        }
        shortUriCreateCachePenetrationBloomFilter.add(suffix);
        return ShortLinkCreateRespDTO.builder()
                .fullShortURL(shortLinkDO.getFullShortUrl())
                .originURL(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        int customGenerateCount = 0;
        String shortUri;
        while (true) {
            // 重试次数超过10次抛出异常
            if (customGenerateCount > 10) {
                throw new ServiceException("短链接频繁生成，请稍后再试");
            }
            String originURL = requestParam.getOriginUrl();
            // 短链接生成重复之后就不用之前的那个长连接再去生成短链接了
            // 所以加上一个时间点改变originUrl
            originURL += System.currentTimeMillis();
            shortUri = HashUtil.hashToBase62(originURL);
            if (!shortUriCreateCachePenetrationBloomFilter.contains(requestParam.getDomain() + "/" + shortUri)) {
                break;
            }
            customGenerateCount++;
        }
        return shortUri;
    }
}
