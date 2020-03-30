package com.cuit.foodmall.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: YX
 * @date: 2020/3/30 10:27
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderAndAmount implements Serializable {

	/*
	日期
	 */
	private String date;
	/*
	订单数
	 */
	private int orders;
	/*
	金额
	 */
	private BigDecimal amount;

}
