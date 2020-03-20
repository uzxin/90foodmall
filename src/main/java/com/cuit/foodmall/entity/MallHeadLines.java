package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: YX
 * @date: 2020/3/19 10:35
 * @description: 商城头条
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("mall_headlines")
public class MallHeadLines extends BasePO{
	/*
	名字
	 */
	@TableField("name")
	private String name;
	/*
	url地址
	 */
	@TableField("url")
	private String url;
	/*
	权重
	 */
	@TableField("weight")
	private String weight;
	/*
	创建人ID
	 */
	@TableField("create_user_id")
	private String createUserId;
	/*
	创建人名字
	 */
	@TableField("create_user_name")
	private String createUserName;
}
