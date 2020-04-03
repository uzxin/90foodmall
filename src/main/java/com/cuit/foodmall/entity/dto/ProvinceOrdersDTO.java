package com.cuit.foodmall.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: YX
 * @date: 2020/4/3 14:41
 * @description:
 */
@Data
public class ProvinceOrdersDTO {

	/*
	省份
	 */
	@TableField("province")
	private String province;
	/*
	支付金额
	 */
	@TableField("pay_amount")
	private BigDecimal payAmount;
}
