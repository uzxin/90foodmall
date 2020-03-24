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
	店铺描述
	 */
	@TableField("description")
	private String description;
	/*
	店铺介绍
	 */
	@TableField("introduction")
	private String introduction;
	/*
	商家ID
	 */
	@TableField("business_id")
	private Long businessId;
	/*
	法人名字
	 */
	@TableField("legal_user_name")
	private String legalUserName;
	/*
	法人身份证号
	 */
	@TableField("legal_user_idcard")
	private String legalUserIdCard;
	/*
	联系人
	 */
	@TableField("contact_user_name")
	private String contactUserName;
	/*
	联系电话
	 */
	@TableField("contact_user_phone")
	private String contactUserPhone;
	/*
	传真
	 */
	@TableField("fax")
	private String fax;
	/*
	邮箱
	 */
	@TableField("email")
	private String email;
	/*
	店铺等级
	 */
	@TableField("level")
	private int level;
	/*
	相符描述
	 */
	@TableField("score_description")
	private float scoreDescription;
	/*
	服务态度
	 */
	@TableField("score_service_attitude")
	private float scoreServiceAttitude;
	/*
	发货速度
	 */
	@TableField("score_delivery_speed")
	private float scoreDeliverySpeed;
	/*
	状态
	 */
	@TableField("status")
	private String status;
	/*
	审核意见
	 */
	@TableField("review_opinion")
	private String reviewOpinion;
	/*
	开通时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-8")
	@TableField("create_time")
	private Date createTime;
}
