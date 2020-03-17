package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: YX
 * @date: 2020/3/12 16:50
 * @description: 退款
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("refund")
public class Refund extends BasePO{
	/*
	订单ID
	 */
	@TableField("order_id")
	private Long orderId;
	/*
	订单号
	 */
	@TableField("order_number")
	private String orderNumber;
	/*
	产品ID
	 */
	@TableField("product_id")
	private Long productId;
	/*
	产品名字
	 */
	@TableField("product_name")
	private String productName;
	/*
	数量
	 */
	@TableField("product_quantity")
	private int productQuantity;
	/*
	退款金额
	 */
	@TableField("refund_amount")
	private BigDecimal refundAmount;
	/*
	交易时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("transaction_time")
	private Date transactionTime;
	/*
	退款原因
	 */
	@TableField("refund_reason")
	private String refundReason;
	/*
	退款人名字
	 */
	@TableField("refund_user_name")
	private String refundUserName;
	/*
	退款人电话
	 */
	@TableField("refund_user_phone")
	private String refundUserPhone;
	/*
	退款方式
	 */
	@TableField("refund_way")
	private String refundWay;
	/*
	状态
	 */
	@TableField("status")
	private Long status;
	/*
	店铺ID
	 */
	@TableField("store_id")
	private Long storeId;
	/*
	操作人
	 */
	@TableField("operator")
	private String operator;
	/*
	退款时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@TableField("update_time")
	private Date updateTime;
}
