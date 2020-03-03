package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/3/2 11:14
 * @description: 商品图片
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("product_image")
public class ProductImage extends BasePO{
	/*
	图片路径
	 */
	@TableField("src")
	private String src;
	/*
	图片类型
	 */
	@TableField("type")
	private String type;
	/*
	商品ID
	 */
	@TableField("product_id")
	private Long productId;
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
