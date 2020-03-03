package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: YX
 * @date: 2020/2/28 09:00
 * @description: 商品分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class Category implements Serializable {

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/*
	父级分类ID
	 */
	@TableField("pid")
	private Long pid;
	/*
	分类名
	 */
	@TableField("name")
	private String name;
	/*
	级别
	 */
	@TableField("level")
	private Long level;
	/*
	图片地址
	 */
	@TableField("image_src")
	private String imageSrc;
	/*
	创建人ID
	 */
	@TableField("create_user_id")
	private Long createUserId;
	/*
	创建人姓名
	 */
	@TableField("create_user_name")
	private String createUserName;
	/**
	 * 逻辑删除标志
	 */
	@TableLogic
	private String delFlag;

	@Override
	public String toString() {
		return "{id:\"" + id + "\",pid:\"" + pid + "\",name:\"" + name + "\",level:\"" + level + "\",imageSrc:\"" + imageSrc + "\"}";
	}
}
