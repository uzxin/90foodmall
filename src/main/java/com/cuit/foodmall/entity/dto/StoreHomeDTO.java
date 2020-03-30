package com.cuit.foodmall.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: YX
 * @date: 2020/3/27 09:28
 * @description: 店铺首页需要展示的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StoreHomeDTO implements Serializable {
	/*
	商品总数
	 */
	private int product_number;
	/*
	待审核商品数
	 */
	private int product_pending_review;
	/*
	通过商品数
	 */
	private int product_psss;
	/*
	未通过商品数
	 */
	private int product_nopass;
	/*
	订单总数
	 */
	private int order_number;
	/*
	已完成订单数
	 */
	private int order_completed;
	/*
	待发货
	 */
	private int order_toBeDelivered;
	/*
	待收货
	 */
	private int order_toBeReceived;
	/*
	评论总数
	 */
	private int review_number;
	/*
	已回复
	 */
	private int review_replied;
	/*
	待回复
	 */
	private int review_pendingReply;
	/*
	退款数
	 */
	private int refund_number;
	/*
	待退款
	 */
	private int refund_pending;
	/*
	已退款
	 */
	private int refund_refunded;
	/*
	物流数
	 */
	private int logistics_number;
	/*
	成交金额
	 */
	private BigDecimal Turnover;
}
