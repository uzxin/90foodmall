package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.mapper.OrderMapper;
import com.cuit.foodmall.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/6 10:19
 * @description:
 */
@Service("OrderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
