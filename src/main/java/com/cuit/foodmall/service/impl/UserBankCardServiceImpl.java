package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.UserBankCard;
import com.cuit.foodmall.mapper.UserBankCardMapper;
import com.cuit.foodmall.service.UserBankCardService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/2/27 14:37
 * @description:
 */
@Service("UserBankCardService")
public class UserBankCardServiceImpl extends ServiceImpl<UserBankCardMapper, UserBankCard> implements UserBankCardService {
}
