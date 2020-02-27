package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/2/27 14:28
 * @description: 银行
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("bank")
public class Bank extends BasePO {
	/*
	银行名称
	 */
	@TableField("bank_name")
	private String bankName;
	/*
	创建人ID
	 */
	@TableField("create_user_id")
	private Long createUserId;
	/*
	创建人姓名
	 */
	@TableField("create_user_name")
	private String createUserName;
}
