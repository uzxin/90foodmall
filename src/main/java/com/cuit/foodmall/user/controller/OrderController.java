package com.cuit.foodmall.user.controller;

import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.service.PayMethodService;
import com.cuit.foodmall.service.ShipMethodService;
import com.cuit.foodmall.util.RandomUtil;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/6 09:32
 * @description: 订单
 */
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController extends BaseController{

	@Autowired
	private OrderService orderService;
	@Autowired
	private ShipMethodService shipMethodService;
	@Autowired
	private PayMethodService payMethodService;

	@PostMapping("create")
	public Object create(Order order, HttpSession session){
		order.setOrderNumber(RandomUtil.get32());//订单号
		order.setUserId(getUser(session).getId());//用户ID
		System.out.println(order);
		return Result.ok();
	}

	/**
	 * @description: 查询物流方式
	 * @return: java.lang.Object
	 */
	@GetMapping("listShipMethod")
	public Object listShipMethod(){
		return Result.ok(shipMethodService.list());
	}

	/**
	 * @description: 查询支付方式
	 * @return: java.lang.Object
	 */
	@GetMapping("listPayMethod")
	public Object listPayMethod(){
		return Result.ok(payMethodService.list());
	}
}
