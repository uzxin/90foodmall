package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: YX
 * @date: 2020/3/12 10:43
 * @description: 物流
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("logistics")
public class Logistics extends BasePO {
	/*
	快递方式ID
	 */
	@TableField("ship_method_id")
	private Long shipMethodId;
	/*
	快递名字
	 */
	@TableField("ship_method_name")
	private String shipMethodName;
	/*
	快递号
	 */
	@TableField("express_number")
	private String expressNumber;
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
	店铺ID
	 */
	@TableField("store_id")
	private Long storeId;
	/*
	创建人ID
	 */
	@TableField("create_user_id")
	private Long createUserId;
	/*
	创建人名字
	 */
	@TableField("create_user_name")
	private String createUserName;
	/*
	发货时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("create_time")
	private Date createTime;
	/*
	更新时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("update_time")
	private Date updateTime;
}
