package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.mapper.UserMapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/2/17 15:14
 * @description:
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
