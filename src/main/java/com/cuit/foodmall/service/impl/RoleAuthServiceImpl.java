package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.RoleAuth;
import com.cuit.foodmall.mapper.RoleAuthMapper;
import com.cuit.foodmall.service.RoleAuthService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/25 15:54
 * @description:
 */
@Service("RoleAuthService")
public class RoleAuthServiceImpl extends ServiceImpl<RoleAuthMapper, RoleAuth> implements RoleAuthService {
}
