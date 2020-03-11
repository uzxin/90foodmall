package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Product;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.mapper.ProductMapper;
import com.cuit.foodmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/2 11:18
 * @description:
 */
@Service("ProductService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	@Override
	public IPage<ProductVO> listProductByCid(Page<ProductVO> page, Long categoryId) {
		return productMapper.listProductByCid(page, categoryId);
	}

	@Override
	public ProductVO getProductById(Long productId) {
		return productMapper.getProductById(productId);
	}

	@Override
	public IPage<ProductVO> listProductBySid(Page<ProductVO> page, QueryWrapper<ProductVO> wrapper) {
		return productMapper.listProductBySid(page, wrapper);
	}

}
