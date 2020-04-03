package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.*;
import com.cuit.foodmall.entity.dto.AdminHomeDTO;
import com.cuit.foodmall.entity.dto.SearchKeyWordDTO;
import com.cuit.foodmall.service.*;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: YX
 * @date: 2020/3/31 16:18
 * @description: 首页数据展示
 */
@RestController
@RequestMapping("admin/home")
public class AdminHomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SearchHistoryService searchHistoryService;

	/**
	 * @description: 统计数据
	 * @return: java.lang.Object
	 */
	@GetMapping("getData")
	public Object getData(){
		AdminHomeDTO dto = new AdminHomeDTO();
		dto.setUsers(userService.count());
		dto.setStores(storeService.count());
		dto.setProducts(productService.count());
		dto.setOrders(orderService.count());
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String lastWeek = LocalDateTime.now().minusDays(6).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String lastMonth = LocalDateTime.now().minusDays(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String lastYear = LocalDateTime.now().minusDays(365).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		//查看本月新增用户量
		dto.setUsers_add_month(userService.count(new QueryWrapper<User>().lambda().between(User::getCreateTime,lastMonth,now)));
		//查看本年新增店铺量
		dto.setStores_add_year(storeService.count(new QueryWrapper<Store>().lambda().between(Store::getCreateTime,lastYear,now)));
		//查看本月新增商品量
		dto.setProducts_add_month(productService.count(new QueryWrapper<Product>().lambda().between(Product::getCreateTime,lastMonth,now)));
		//查看本周新增订单量
		dto.setOrders_add_week(orderService.count(new QueryWrapper<Order>().lambda().between(Order::getCreateTime,lastWeek,now)));
		return Result.ok(dto);
	}


	/**
	 * @description: 关键词搜索排行榜
	 * @return: java.lang.Object
	 */
	@GetMapping("getTopSearchHistory")
	public Object getTopSearchHistory(){
		List<SearchKeyWordDTO> list = searchHistoryService.listSearchNum();
		return Result.ok(list);
	}
}
