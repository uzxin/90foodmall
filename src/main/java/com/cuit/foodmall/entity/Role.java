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
	/*
	创建人姓名
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
