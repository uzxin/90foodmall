package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.UserRole;
import com.cuit.foodmall.mapper.UserRoleMapper;
import com.cuit.foodmall.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/23 13:33
 * @description:
 */
@Service("UserRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
