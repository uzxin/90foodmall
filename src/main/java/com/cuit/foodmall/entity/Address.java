package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: YX
 * @date: 2020/2/26 15:31
 * @description: 全国市区县码表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("address")
public class Address extends BasePO{
	/*
	父及地区关系
	 */
	@TableField("pid")
	private Long pid;
	/*
	地区名称
	 */
	@TableField("district")
	private String district;
	/*
	级别，0表示国家，1表示省、直辖市、自治区，2表示市，3表示县、区
	 */
	@TableField("level")
	private int level;
}
