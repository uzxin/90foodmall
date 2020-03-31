package com.cuit.foodmall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.entity.vo.OrderVO;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.entity.vo.ProfitVO;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/6 10:17
 * @description:
 */
public interface OrderService extends IService<Order> {
	/**
	 * @description: 分页查询店铺所有订单
	 * @param: ipage
	 * @param: wrappe
	 * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.cuit.foodmall.entity.Order>
	 */
	IPage<OrderVO> listOrderBySid(Page<OrderVO> ipage, QueryWrapper<OrderVO> wrapper);

	/**
	 * @description: 查询用户订单
	 * @param: wrapper
	 * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.cuit.foodmall.entity.Order>
	 */
	List<OrderVO> listOrderByUid(QueryWrapper<OrderVO> wrapper);

	/**
	 * @description: 查询订单详情
	 * @param: orderId
	 * @return: java.lang.String
	 */
	OrderVO getByOrderId(Long orderId);

	/**
	 * @description: 查询最近七天的订单
	 * @param: storeId
	 * @return: java.util.List<com.cuit.foodmall.entity.Order>
	 */
	List<OrderVO> listForTheLastSevenDays(Long storeId);

	/**
	 * @description: 查询利润
	 * @param: wrapper
	 * @return: java.util.List<com.cuit.foodmall.entity.vo.ProfitVO>
	 */
	List<ProfitVO> listProfit(QueryWrapper<ProfitVO> wrapper);
}
