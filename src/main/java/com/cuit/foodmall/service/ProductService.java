package com.cuit.foodmall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.foodmall.entity.Product;
import com.cuit.foodmall.entity.vo.ProductVO;

/**
 * @author: YX
 * @date: 2020/3/2 11:17
 * @description:
 */
public interface ProductService extends IService<Product> {
	/**
	 * @description: 根据分类查询商品
	 * @param: page
	 * @param: categoryId
	 * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.cuit.foodmall.entity.vo.ProductVO>
	 */
	public IPage<ProductVO> listProductByCid(Page<ProductVO> page, Long categoryId);
	/**
	 * @description: 根据ID查询商品
	 * @param: productId
	 * @return: ProductVO
	 */
	ProductVO getProductById(Long productId);

	/**
	 * @description: 根据店铺ID查询商品
	 * @param: page
	 * @param: wrapper
	 * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.cuit.foodmall.entity.vo.ProductVO>
	 */
	IPage<ProductVO> listProductBySid(Page<ProductVO> page, QueryWrapper<ProductVO> wrapper);
}
