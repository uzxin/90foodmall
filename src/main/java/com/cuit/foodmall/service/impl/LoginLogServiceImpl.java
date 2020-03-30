package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.LoginLog;
import com.cuit.foodmall.mapper.LoginLogMapper;
import com.cuit.foodmall.service.LoginLogService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/26 17:41
 * @description:
 */
@Service("LoginLogService")
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
}
