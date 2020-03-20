package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.ProductProperty;
import com.cuit.foodmall.entity.vo.ProductPropertyVO;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.mapper.ProductPropertyMapper;
import com.cuit.foodmall.service.ProductPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/3 08:55
 * @description:
 */
@Service("ProductPropertyService")
public class ProductPropertyServiceImpl extends ServiceImpl<ProductPropertyMapper, ProductProperty> implements ProductPropertyService {
	@Autowired
	private ProductPropertyMapper productPropertyMapper;

	@Override
	public IPage<ProductPropertyVO> listBySid(Page<ProductPropertyVO> ipage, QueryWrapper<ProductVO> wrapper) {
		return productPropertyMapper.listBySid(ipage, wrapper);
	}
}
