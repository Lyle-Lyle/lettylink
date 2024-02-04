package com.letty.lettylink.application.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letty.lettylink.application.dao.entity.ShortLinkDO;
import com.letty.lettylink.application.dao.mapper.ShortLinkMapper;
import com.letty.lettylink.application.service.ShortLinkService;
import org.springframework.stereotype.Service;

@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {


}
