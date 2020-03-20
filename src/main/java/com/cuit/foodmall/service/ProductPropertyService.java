package com.cuit.foodmall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.foodmall.entity.ProductProperty;
import com.cuit.foodmall.entity.vo.ProductPropertyVO;
import com.cuit.foodmall.entity.vo.ProductVO;

/**
 * @author: YX
 * @date: 2020/3/3 08:53
 * @description:
 */
public interface ProductPropertyService extends IService<ProductProperty> {
	/**
	 * @description: 查询店铺旗下商品的属性
	 * @param: ipage
	 * @param: wrapper
	 * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.cuit.foodmall.entity.ProductProperty>
	 */
	IPage<ProductPropertyVO> listBySid(Page<ProductPropertyVO> ipage, QueryWrapper<ProductVO> wrapper);
}
