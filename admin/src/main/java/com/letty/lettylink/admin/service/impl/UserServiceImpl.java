package com.letty.lettylink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letty.lettylink.admin.common.convention.exception.ClientException;
import com.letty.lettylink.admin.common.convention.exception.ServiceException;
import com.letty.lettylink.admin.common.enums.UserErrorCodeEnum;
import com.letty.lettylink.admin.dao.entity.UserDO;
import com.letty.lettylink.admin.dao.mapper.UserMapper;
import com.letty.lettylink.admin.dto.req.UserLoginReqDTO;
import com.letty.lettylink.admin.dto.req.UserRegisterReqDTO;
import com.letty.lettylink.admin.dto.req.UserUpdateReqDTO;
import com.letty.lettylink.admin.dto.resp.UserLoginRespDTO;
import com.letty.lettylink.admin.dto.resp.UserRespDTO;
import com.letty.lettylink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.letty.lettylink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.letty.lettylink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static com.letty.lettylink.admin.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                        .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ServiceException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);

        return result;
    }

    // 检查用户名是否存在
    @Override
    public Boolean hasUsername(String username) {
//        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
//                .eq(UserDO::getUsername, username);
//        UserDO userDO = baseMapper.selectOne(queryWrapper);
//        return userDO == null;
        // 使用布隆过滤器
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (!hasUsername(requestParam.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        // 获取到锁 就注册
       try {
           if (lock.tryLock()) {
               int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
               if (inserted < 1) {
                   throw new ClientException(USER_SAVE_ERROR);
               }
               userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
               return;
           }
           throw new ClientException(USER_NAME_EXIST);
       } finally {
           lock.unlock();
       }

    }

    @Override
    public void update(UserUpdateReqDTO requestParam) {
        // TODO 验证传入的这个用户信息和当前登录的用户是否匹配

        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername());
        baseMapper.update(BeanUtil.toBean(requestParam, UserDO.class), updateWrapper);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException("用户不存在");
        }
        Boolean hasLogin = stringRedisTemplate.hasKey("login_" + requestParam.getUsername());
        if (hasLogin != null && hasLogin) {
            throw new ClientException("用户已登录");
        }

        /**
         * Hash
         * key: login_用户名
         * value:
         *  key: token标识
         *  val: JSON字符串(用户信息)
         */

        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put("login_" + requestParam.getUsername(), uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_" + requestParam.getUsername(), 30L, TimeUnit.DAYS);
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        return  stringRedisTemplate.opsForHash().get("login_" + username, token ) != null;
    }

    @Override
    public void logout(String username, String token) {
        if (checkLogin(username, token)) {
            stringRedisTemplate.delete("login_" + username);
            return;
        }
        throw new ClientException("用户token不存在或用户未登录");

    }

}
