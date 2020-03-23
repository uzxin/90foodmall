package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/3/23 12:26
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("user_role")
public class UserRole extends BasePO {
	/*
	用户ID
	 */
	@TableField("user_id")
	private Long userId;
	/*
	角色ID
	 */
	@TableField("role_id")
	private Long roleId;
	/*
	角色名字
	 */
	@TableField("role_name")
	private String roleName;
}
