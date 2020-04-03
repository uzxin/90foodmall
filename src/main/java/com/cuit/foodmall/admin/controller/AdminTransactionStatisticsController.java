package com.cuit.foodmall.admin.controller;

import com.cuit.foodmall.entity.dto.ProvinceOrdersDTO;
import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

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

		return Result.ok();
	}
}
