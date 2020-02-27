package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/2/27 14:31
 * @description: 用户绑定银行卡
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("user_bankcard")
public class UserBankCard extends BasePO{
	/*
	用户ID
	 */
	@TableField("user_id")
	private Long userId;
	/*
	银行ID
	 */
	@TableField("bank_id")
	private Long bankId;
	/*
	银行名称
	 */
	@TableField("bank_name")
	private String bankName;
	/*
	银行卡号
	 */
	@TableField("bank_card_number")
	private String bankCardNumber;
	/*
	银行卡类型
	 */
	@TableField("bank_card_type")
	private String bankCardType;
}
