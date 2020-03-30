package com.cuit.foodmall.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: YX
 * @date: 2020/3/30 16:22
 * @description: 收支统计
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RevenueAndExpenditure implements Serializable {

	/*
	日期
	 */
	private String date;
	/*
	收入
	 */
	private BigDecimal income;
	/*
	支出
	 */
	private BigDecimal expenditure;
	/*
	利润
	 */
	private BigDecimal profit;
}
