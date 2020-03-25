package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Auth;
import com.cuit.foodmall.mapper.AuthMapper;
import com.cuit.foodmall.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/25 14:53
 * @description:
 */
@Service("AuthService")
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthService {
}
