package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/2/20 10:45
 * @description: 用户个人信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("user_information")
public class UserInformation extends BasePO{
	/*
	用户ID
	 */
	@TableField("user_id")
	private Long userId;
	/*
	用户名
	 */
	@TableField("user_username")
	private String username;
	/*
	真实姓名
	 */
	@TableField("user_realname")
	private String realName;
	/*
	昵称
	 */
	@TableField("user_nickname")
	private String nickName;
	/*
	头像
	 */
	@TableField("user_headImg_src")
	private String headImgSrc;
	/*
	性别(0为保密，1为男性，2为女性)
	 */
	@TableField("user_gender")
	private String gender;
	/*
	生日
	 */
	@TableField("user_birthday")
	private String birthday;
	/*r
	电话
	 */
	@TableField("user_phone")
	private String phone;
	/*
	邮箱
	 */
	@TableField("user_email")
	private String email;
}
