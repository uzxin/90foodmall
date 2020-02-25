package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/2/25 14:24
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("user_question")
public class UserQuestion extends BasePO{
	/*
	用户ID
	 */
	@TableField("user_id")
	private Long userId;
	/*
	问题ID
	 */
	@TableField("question_id")
	private Long questionId;
	/*
	问题答案
	 */
	@TableField("answer")
	private String answer;
}
