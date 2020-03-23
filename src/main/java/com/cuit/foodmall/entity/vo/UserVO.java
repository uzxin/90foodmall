package com.cuit.foodmall.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cuit.foodmall.entity.User;
import lombok.Data;

/**
 * @author: YX
 * @date: 2020/3/23 09:33
 * @description:
 */
@Data
public class UserVO extends User {

	/*
	昵称
	 */
	@TableField("nickName")
	private String nickName;
	/*
	性别(0为保密，1为男性，2为女性)
	 */
	@TableField("gender")
	private String gender;
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
