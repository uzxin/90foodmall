package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/2/25 14:20
 * @description: 密保问题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("security_question")
public class SecurityQuestion extends BasePO {
	/*
	密保问题
	 */
	@TableField("question")
	private String question;
	/*
	创建人ID
	 */
	@TableField("create_user_id")
	private Long createUserId;
	/*
	创建人
	*/
	@TableField("create_user_name")
	private String createUserName;
}
