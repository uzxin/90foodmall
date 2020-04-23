package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: YX
 * @date: 2020/4/23 14:07
 * @description: 意见
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("opinion")
public class Opinion extends BasePO{

	/*
	问题类型
	 */
	@TableField("opinion_type")
	private String opinionType;
	/*
	问题内容
	 */
	@TableField("opinion_content")
	private String opinionContent;
	/*
	操作人
	 */
	@TableField("operator")
	private String operator;
	/*
	创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("create_time")
	private Date createTime;
}
