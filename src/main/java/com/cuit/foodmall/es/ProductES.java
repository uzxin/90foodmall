package com.cuit.foodmall.es;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: YX
 * @date: 2020/4/1 15:21
 * @description: 用于搜索的商品
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("product")
@Document(indexName = "product",type = "docs")
public class ProductES implements Serializable{

	@TableId(value = "id", type = IdType.AUTO)
	@Id
	private Long id;

	@TableField("name")
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String name; //名字

	@TableField("category")
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String category;// 分类

	@TableField("storeName")
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String storeName; // 店铺

	@TableField("price_sale")
	@Field(type = FieldType.Double)
	private BigDecimal priceSale;// 价格

	@TableField("sales")
	@Field(type = FieldType.Double)
	private BigDecimal sales;// 销量

	@TableField("src")
	@Field(index = false, type = FieldType.Keyword)
	private String src; // 图片地址

}
