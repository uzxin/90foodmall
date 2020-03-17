package com.cuit.foodmall.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cuit.foodmall.entity.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author: YX
 * @date: 2020/3/11 16:23
 * @description:
 */
@Data
public class OrderVO extends Order {

	/*
	商品名字
	 */
	@TableField("productName")
	private String productName;
	/*
	商品名字
	 */
	@TableField("src")
	private String src;
	/*
	下单时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@TableField ("create_time")
	private Date createTime;
	/*
	状态名字
	 */
	@TableField("statusName")
	private String statusName;
	/*
	快递方式
	 */
	@TableField("shipMethodName")
	private String shipMethodName;
	/*
	下单人
	 */
	private String username;
	/*
	收货人姓名
	 */
	private String contactPeopleName;
	/*
	收货人电话
	 */
	private String contactPeoplePhone;
	/*
	收货地址
	 */
	private String address;
	/*
	支付方式
	 */
	private String payMethodName;
}
