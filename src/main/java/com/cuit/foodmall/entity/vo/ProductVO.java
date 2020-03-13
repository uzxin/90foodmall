package com.cuit.foodmall.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: YX
 * @date: 2020/3/2 11:33
 * @description: 商品
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class ProductVO implements Serializable {

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
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
	审核意见
	 */
	@TableField("review_opinion")
	private String reviewOpinion;
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
	/*
	商品图片
	 */
	@TableField("src")
	private String src;
	/*
	分类名字
	 */
	@TableField("categoryName")
	private String categoryName;
	/*
	店铺名字
	 */
	@TableField("storeName")
	private String storeName;
	/*
	状态名字
	 */
	@TableField("statusName")
	private String statusName;
	/*
	创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@TableField("create_time")
	private Date createTime;
	/**
	 * 逻辑删除标志
	 */
	@TableLogic
	private String delFlag;

	@Override
	public String toString() {
		return "{id:\"" + id + "\",name:\"" + name + "\",priceCost:\"" + priceCost + "\",priceOriginal:\"" + priceOriginal + "\",priceSale:\"" + priceSale + "\",sales:\"" + sales + "\",inventory:\"" + inventory + "\",categoryId:\"" + categoryId + "\",enabled:\"" + enabled + "\",createUserId:\"" + createUserId + "\",createUserName:\"" + createUserName +"\",src:\"" + src + "\",delFlag:\"" + delFlag + "\"}";
	}
}
