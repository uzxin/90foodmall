package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/2/26 15:35
 * @description: 收货地址
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("user_address")
public class UserAddress extends BasePO{
	/*
	用户ID
	 */
	@TableField("user_id")
	private Long userId;
	/*
	地址ID
	 */
	@TableField("address_id")
	private Long addressId;
	/*
	详细地址
	 */
	@TableField("detailed_address")
	private String detailedAddress;
	/*
	邮编
	 */
	@TableField("post_code")
	private String postCode;
	/*
	收件人姓名
	 */
	@TableField("contact_people_name")
	private String contactPeopleName;
	/*
	收件人电话
	 */
	@TableField("contact_people_phone")
	private String contactPeoplePhone;
	/*
	是否默认地址，1为是，0为否
	 */
	@TableField("default_flag")
	private String defaultFlag;
}
