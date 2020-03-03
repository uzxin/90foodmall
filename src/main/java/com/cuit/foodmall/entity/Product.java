package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author: YX
 * @date: 2020/3/2 11:00
 * @description: 商品
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("product")
public class Product extends BasePO{
	/*
	商品名字
	 */
	@TableField("name")
	private String name;
	/*
	成本价
	 */
	@TableField("price_cost")
	private BigDecimal priceCost;
	/*
	原价
	 */
	@TableField("price_original")
	private BigDecimal priceOriginal;
	/*
	卖价
	 */
	@TableField("price_sale")
	private BigDecimal priceSale;
	/*
	销量
	 */
	@TableField("sales")
	private BigDecimal sales;
	/*
	库存
	 */
	@TableField("inventory")
	private BigDecimal inventory;
	/*
	分类
	 */
	@TableField("category_id")
	private Long categoryId;
	/*
	是否上架
	 */
	@TableField("enabled")
	private String enabled;
	/*
	创建人id
	 */
	@TableField("create_user_id")
	private Long createUserId;
	/*
	创建人名字
	 */
	@TableField("create_user_name")
	private String createUserName;
}
