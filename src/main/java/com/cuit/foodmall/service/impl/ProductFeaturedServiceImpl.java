package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.ProductFeatured;
import com.cuit.foodmall.mapper.ProductFeaturedMapper;
import com.cuit.foodmall.service.ProductFeaturedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/4/3 10:31
 * @description:
 */
@Service("ProductFeaturedService")
public class ProductFeaturedServiceImpl extends ServiceImpl<ProductFeaturedMapper, ProductFeatured> implements ProductFeaturedService {

	@Autowired
	private ProductFeaturedMapper productFeaturedMapper;

	@Override
	public IPage<ProductFeatured> listAll(Page<ProductFeatured> ipage, LambdaQueryWrapper<ProductFeatured> wrapper) {
		return productFeaturedMapper.listAll(ipage,wrapper);
	}

	@Override
	public List<ProductFeatured> getAll(LambdaQueryWrapper<ProductFeatured> wrapper) {
		return productFeaturedMapper.getAll(wrapper);
	}

	@Override
	public ProductFeatured getOneById(Long productId) {
		return productFeaturedMapper.getOneById(productId);
	}
}
