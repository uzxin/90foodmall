package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/3/3 08:48
 * @description: 产品属性
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("product_property")
public class ProductProperty extends BasePO {
	/*
	商品ID
	 */
	@TableField("product_id")
	private Long productId;
	/*
	属性ID
	 */
	@TableField("property_id")
	private Long propertyId;
	/*
	属性名字
	 */
	@TableField("property_name")
	private String propertyName;
	/*
	属性值
	 */
	@TableField("property_value")
	private String propertyValue;
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
