package com.cuit.foodmall.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.entity.vo.OrderVO;

/**
 * @author: YX
 * @date: 2020/3/6 10:17
 * @description:
 */
public interface OrderService extends IService<Order> {
	/**
	 * @description: 查询店铺所有订单
	 * @param: ipage
	 * @param: wrappe
	 * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.cuit.foodmall.entity.Order>
	 */
	IPage<OrderVO> listOrderBySid(Page<OrderVO> ipage, QueryWrapper<OrderVO> wrapper);
}
