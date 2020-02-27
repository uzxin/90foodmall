package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Bank;
import com.cuit.foodmall.mapper.BankMapper;
import com.cuit.foodmall.service.BankService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/2/27 14:36
 * @description:
 */
@Service("BankService")
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank> implements BankService {
}
