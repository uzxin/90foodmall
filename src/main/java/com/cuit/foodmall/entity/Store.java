package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author: YX
 * @date: 2020/3/9 11:03
 * @description: 店铺
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("store")
public class Store extends BasePO{
	/*
	店铺名字
	 */
	@TableField("name")
	private String name;
	/*
	店铺头像
	 */
	@TableField("headImg")
	private String headImg;
	/*
	店铺类型
	 */
	@TableField("store_type")
	private String storeType;
	/*
	店铺等级
	 */
	@TableField("level")
	private String level;
	/*
	商家ID
	 */
	@TableField("business_id")
	private Long businessId;
	/*
	开通时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-8")
	@TableField("create_time")
	private Date createTime;
	/*
	相符描述
	 */
	@TableField("score_description")
	private String scoreDescription;
	/*
	服务态度
	 */
	@TableField("score_service_attitude")
	private String scoreServiceAttitude;
	/*
	发货速度
	 */
	@TableField("score_delivery_speed")
	private String scoreDeliverySpeed;
}
