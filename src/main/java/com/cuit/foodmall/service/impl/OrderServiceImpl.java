package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.entity.vo.OrderVO;
import com.cuit.foodmall.mapper.OrderMapper;
import com.cuit.foodmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/6 10:19
 * @description:
 */
@Service("OrderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public IPage<OrderVO> listOrderBySid(Page<OrderVO> ipage, QueryWrapper<OrderVO> wrapper) {
		return orderMapper.listOrderBySid(ipage, wrapper);
	}
}
