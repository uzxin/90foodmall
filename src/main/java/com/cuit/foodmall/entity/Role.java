package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/3/23 12:18
 * @description: 角色
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("role")
public class Role extends BasePO{

	/*
	角色名
	 */
	@TableField("role_name")
	private String name;
	/*
	描述
	 */
	@TableField("description")
	private String description;
}
