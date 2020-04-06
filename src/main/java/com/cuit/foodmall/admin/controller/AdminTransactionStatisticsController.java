package com.cuit.foodmall.admin.controller;

import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.entity.dto.OrderAndAmount;
import com.cuit.foodmall.entity.dto.ProvinceOrdersDTO;
import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author: YX
 * @date: 2020/4/3 14:44
 * @description: 交易统计
 */
@RestController
@RequestMapping("admin/transactionStatistics")
public class AdminTransactionStatisticsController {

	@Autowired
	private OrderService orderService;

	/**
	 * @description: 按照省份统计订单量和销售额
	 * @return: java.lang.Object
	 */
	@GetMapping("getOrdersAndAmountByProvince")
	public Object getOrdersAndAmountByProvince(){
		List<ProvinceOrdersDTO> list = orderService.listProvinceOrders();
		HashMap<String, BigDecimal> map = new HashMap<>();
		for (ProvinceOrdersDTO p : list) {
			if(!map.containsKey(p.getProvince())){
				map.put(p.getProvince(),p.getPayAmount());
			}else {
				String key = p.getProvince();
				BigDecimal value = map.get(key);
				value = value.add(p.getPayAmount());
				map.put(key,value);
			}
		}
		return Result.ok(map);
	}

	/**
	 * @description: 统计12个月统计订单量和销售额
	 * @return: java.lang.Object
	 */
	@GetMapping("getOrdersAndAmountByMonth")
	public Object getOrdersAndAmountByMonth(){
		OrderAndAmount month1 = new OrderAndAmount("2020-01",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month2 = new OrderAndAmount("2020-02",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month3 = new OrderAndAmount("2020-03",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month4 = new OrderAndAmount("2020-04",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month5 = new OrderAndAmount("2020-05",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month6 = new OrderAndAmount("2020-06",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month7 = new OrderAndAmount("2020-07",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month8 = new OrderAndAmount("2020-08",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month9 = new OrderAndAmount("2020-09",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month10 = new OrderAndAmount("2020-10",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month11 = new OrderAndAmount("2020-11",0,new BigDecimal(BigInteger.ZERO));
		OrderAndAmount month12 = new OrderAndAmount("2020-12",0,new BigDecimal(BigInteger.ZERO));
		List<Order> list = orderService.list();
		for (Order order : list) {
			switch (order.getCreateTime().getMonth()+1){
				case 1:month1.setOrders(month1.getOrders()+1);month1.setAmount(month1.getAmount().add(order.getPayAmount()));break;
				case 2:month2.setOrders(month2.getOrders()+1);month2.setAmount(month2.getAmount().add(order.getPayAmount()));break;
				case 3:month3.setOrders(month3.getOrders()+1);month3.setAmount(month3.getAmount().add(order.getPayAmount()));break;
				case 4:month4.setOrders(month4.getOrders()+1);month4.setAmount(month4.getAmount().add(order.getPayAmount()));break;
				case 5:month5.setOrders(month5.getOrders()+1);month5.setAmount(month5.getAmount().add(order.getPayAmount()));break;
				case 6:month6.setOrders(month6.getOrders()+1);month6.setAmount(month6.getAmount().add(order.getPayAmount()));break;
				case 7:month7.setOrders(month7.getOrders()+1);month7.setAmount(month7.getAmount().add(order.getPayAmount()));break;
				case 8:month8.setOrders(month8.getOrders()+1);month8.setAmount(month8.getAmount().add(order.getPayAmount()));break;
				case 9:month9.setOrders(month9.getOrders()+1);month9.setAmount(month9.getAmount().add(order.getPayAmount()));break;
				case 10:month10.setOrders(month10.getOrders()+1);month10.setAmount(month10.getAmount().add(order.getPayAmount()));break;
				case 11:month11.setOrders(month11.getOrders()+1);month11.setAmount(month11.getAmount().add(order.getPayAmount()));break;
				case 12:month12.setOrders(month12.getOrders()+1);month12.setAmount(month12.getAmount().add(order.getPayAmount()));break;
			}
		}
		return Result.ok(Arrays.asList(month1,month2,month3,month4,month5,month6,month7,month8,month9,month10,month11,month12));
	}

	/**
	 * @description: 根据订单量店铺排序
	 * @return: java.lang.Object
	 */
	@GetMapping("getStoreByOrders")
	public Object getStoreByOrders(){
		return Result.ok(orderService.getStoreByOrders());
	}

	/**
	 * @description: 根据交易额店铺排序
	 * @return: java.lang.Object
	 */
	@GetMapping("getStoreByAmount")
	public Object getStoreByAmount(){
		return Result.ok(orderService.getStoreByAmount());
	}
}
