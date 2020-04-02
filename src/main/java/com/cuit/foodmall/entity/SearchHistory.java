package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author: YX
 * @date: 2020/4/2 14:24
 * @description: 搜索记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("search_history")
public class SearchHistory extends BasePO {

	@TableField("user_id")
	private Long userId;

	@TableField("user_name")
	private String username;

	/*
	搜索关键词
	 */
	@TableField("keyword")
	private String keyword;

	@TableField("create_time")
	private Date createTime;
}
