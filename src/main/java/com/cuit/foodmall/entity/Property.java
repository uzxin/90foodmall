package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/3/3 08:46
 * @description: 商品属性码表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("property")
public class Property extends BasePO {
	/*
	属性名称
	 */
	@TableField("name")
	private String name;
	/*
	创建人id
	 */
	@TableField("create_user_id")
	private Long createUserId;
	/*
	创建人名字
	 */
	@TableField("create_user_name")
	private String createUserName;
}
