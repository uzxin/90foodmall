package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.PayMethod;
import com.cuit.foodmall.mapper.PayMethodMapper;
import com.cuit.foodmall.service.PayMethodService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/6 10:19
 * @description:
 */
@Service("PayMethodService")
public class PayMethodServiceImpl extends ServiceImpl<PayMethodMapper, PayMethod> implements PayMethodService {
}
