package com.cuit.foodmall.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cuit.foodmall.entity.Product;
import lombok.Data;

/**
 * @author: YX
 * @date: 2020/3/2 11:33
 * @description: 商品
 */
@Data
public class ProductVO extends Product {
	/*
	商品图片
	 */
	@TableField("src")
	private String src;
}
