package com.cuit.foodmall.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: YX
 * @date: 2020/3/31 09:19
 * @description: 利润
 */
@Data
public class ProfitVO implements Serializable {
	/*
	创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	@TableField("create_time")
	private Date createTime;
	/*
	成本
	 */
	@TableField("cost")
	private BigDecimal cost;
	/*
	支付金额
	 */
	@TableField("pay_amount")
	private BigDecimal payAmount;
	/*
	利润
	 */
	private BigDecimal profit;
}
