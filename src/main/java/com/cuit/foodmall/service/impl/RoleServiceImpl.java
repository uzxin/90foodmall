package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Role;
import com.cuit.foodmall.mapper.RoleMapper;
import com.cuit.foodmall.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/23 12:20
 * @description:
 */
@Service("RoleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
