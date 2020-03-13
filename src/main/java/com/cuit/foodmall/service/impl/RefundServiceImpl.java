package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Refund;
import com.cuit.foodmall.mapper.RefundMapper;
import com.cuit.foodmall.service.RefundService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/12 17:06
 * @description:
 */
@Service("RefundService")
public class RefundServiceImpl extends ServiceImpl<RefundMapper, Refund> implements RefundService {
}
