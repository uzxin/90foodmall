package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author: YX
 * @date: 2020/3/6 10:05
 * @description: 订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("order")
public class Order extends BasePO{

	/*
	订单号
	 */
	@TableField("order_number")
	private String orderNumber;
	/*
	商品ID
	 */
	@TableField("product_id")
	private Long productId;
	/*
	单价
	 */
	@TableField("product_price")
	private BigDecimal productPrice;
	/*
	数量
	 */
	@TableField("product_quantity")
	private int productQuantity;
	/*
	用户
	 */
	@TableField("user_id")
	private Long userId;
	/*
	收货地址
	 */
	@TableField("user_address_id")
	private Long userAddressId;
	/*
	物流方式
	 */
	@TableField("ship_method_id")
	private Long shipMethodId;
	/*
	支付方式
	 */
	@TableField("pay_method_id")
	private Long payMethodId;
	/*
	支付金额
	 */
	@TableField("pay_amount")
	private BigDecimal payAmount;
	/*
	订单状态
	 */
	@TableField("status")
	private String status;

}
