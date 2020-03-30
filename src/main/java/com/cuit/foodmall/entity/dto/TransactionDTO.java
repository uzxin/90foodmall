package com.cuit.foodmall.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: YX
 * @date: 2020/3/30 08:53
 * @description: 交易统计
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDTO implements Serializable {

	/*
	订单总数
	 */
	private int totalOrders;
	/*
	待付款
	 */
	private int pendingPayment;
	/*
	待发货
	 */
	private int toBeDelivered;
	/*
	待收货
	 */
	private int toBeReceived;
	/*
	待评价
	 */
	private int comment;
	/*
	待退款
	 */
	private int pendingRefund;
	/*
	已退款
	 */
	private int refunded_orders;
	/*
	已完成
	 */
	private int completed;
	/*
	预计收入
	 */
	private BigDecimal estimatedIncome;
	/*
	已到账
	 */
	private BigDecimal Arrived;
	/*
	未到账
	 */
	private BigDecimal Unaccounted;
	/*
	已退款
	 */
	private BigDecimal refunded;
	/*
	未退款
	 */
	private BigDecimal notRefunded;

}
