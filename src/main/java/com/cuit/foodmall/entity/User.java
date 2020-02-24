package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/2/17 15:15
 * @description: 用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("user")
public class User extends BasePO{
	/*
	用户名
	 */
	@TableField("user_username")
	private String username;
	/*
	登录密码
	 */
	@TableField("user_password")
	private String password;
	/*
	支付密码
	 */
	@TableField("user_pay_password")
	private String payPassword;
	/*
	帐号状态，正常(0),封号(1)
	 */
	@TableField("user_account_status")
	private String accountStatus;
}
