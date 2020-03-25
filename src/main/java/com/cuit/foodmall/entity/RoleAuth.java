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
 * @date: 2020/3/25 15:51
 * @description: 角色权限
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("role_auth")
public class RoleAuth extends BasePO{

	/*
	角色ID
	 */
	@TableField("role_id")
	private Long roleId;
	/*
	权限ID
	 */
	@TableField("auth_id")
	private Long authId;
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
