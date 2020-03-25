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
 * @date: 2020/3/25 14:48
 * @description: 权限
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("auth")
public class Auth extends BasePO{

	/*
	名字
	 */
	@TableField("name")
	private String name;
	/*
	描述
	 */
	@TableField("description")
	private String description;
	/*
	父节点
	 */
	@TableField("pid")
	private Long pid;
	/*
	级别
	 */
	@TableField("level")
	private Long level;
	/*
	url地址
	 */
	@TableField("address")
	private String address;
	/*
	图标
	 */
	@TableField("icon")
	private String icon;
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
	创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("create_time")
	private Date createTime;
}
