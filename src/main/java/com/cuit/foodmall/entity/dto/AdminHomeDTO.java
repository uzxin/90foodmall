package com.cuit.foodmall.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: YX
 * @date: 2020/3/31 16:21
 * @description: 商城后台首页数据展示
 */
@Data
public class AdminHomeDTO implements Serializable {

	/*
	用户总量
	 */
	private int users;
	/*
	新增用户数
	 */
	private int users_add_month;
	/*
	店铺总量
	 */
	private int stores;
	/*
	新增店铺数
	 */
	private int stores_add_year;
	/*
	商品总量
	 */
	private int products;
	/*
	新增商品数
	 */
	private int products_add_month;
	/*
	订单总量
	 */
	private int orders;
	/*
	新增订单数
	 */
	private int orders_add_week;
}
