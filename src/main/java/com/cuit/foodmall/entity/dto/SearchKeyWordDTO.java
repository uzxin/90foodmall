package com.cuit.foodmall.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/4/2 17:02
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchKeyWordDTO {
	/*
	关键词
	 */
	@TableField("keyword")
	private String keyword;
	/*
	搜索次数
	 */
	@TableField("search_num")
	private int searchNum;
	/*
	搜索人数
	 */
	private int userNum;
}
