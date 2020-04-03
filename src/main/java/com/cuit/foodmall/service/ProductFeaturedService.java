package com.cuit.foodmall.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.foodmall.entity.ProductFeatured;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/4/3 10:30
 * @description:
 */
public interface ProductFeaturedService extends IService<ProductFeatured> {
	/**
	 * @description:
	 * @param: ipage
	 * @param: wrapper
	 * @return: java.lang.String
	 */
	IPage<ProductFeatured> listAll(Page<ProductFeatured> ipage, LambdaQueryWrapper<ProductFeatured> wrapper);

	/**
	 * @description:
	 * @param: wrapper
	 * @return: java.util.List<com.cuit.foodmall.entity.ProductFeatured>
	 */
	List<ProductFeatured> getAll(LambdaQueryWrapper<ProductFeatured> wrapper);

	/**
	 * @description:
	 * @param: productId
	 * @return: java.lang.Object
	 */
	ProductFeatured getOneById(Long productId);
}
