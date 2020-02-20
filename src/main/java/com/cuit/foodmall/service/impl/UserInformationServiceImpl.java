package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.UserInformation;
import com.cuit.foodmall.mapper.UserInformationMapper;
import com.cuit.foodmall.service.UserInformationService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/2/20 10:45
 * @description:
 */
@Service("UserInformationService")
public class UserInformationServiceImpl extends ServiceImpl<UserInformationMapper, UserInformation> implements UserInformationService {
}
