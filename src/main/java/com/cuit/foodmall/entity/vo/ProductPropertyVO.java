package com.cuit.foodmall.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cuit.foodmall.entity.ProductProperty;
import lombok.Data;

/**
 * @author: YX
 * @date: 2020/3/20 09:52
 * @description:
 */
@Data
public class ProductPropertyVO extends ProductProperty {

	/*
	商品名
	 */
	@TableField("productName")
	private String productName;
}
