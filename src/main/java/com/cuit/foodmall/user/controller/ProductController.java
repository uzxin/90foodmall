package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.ProductImage;
import com.cuit.foodmall.entity.ProductProperty;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.service.ProductImageService;
import com.cuit.foodmall.service.ProductPropertyService;
import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: YX
 * @date: 2020/3/2 10:45
 * @description: 商品
 */
@RestController
@RequestMapping("product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ProductPropertyService productPropertyService;

	/**
	 * @description: 根据分类查询商品
	 * @param: categoryId
	 * @return: java.lang.Object
	 */
	@GetMapping("category/{categoryId}")
	public Object listProduct(@PathVariable("categoryId") Long categoryId,
	                          @RequestParam(required = false,defaultValue = "1") int page,
	                          @RequestParam(required = false,defaultValue = "12") int limit){
		Page<ProductVO> ipage = new Page<>(page, limit);
		IPage<ProductVO> p = productService.listProductByCid(ipage, categoryId);
		return Result.ok(p);
	}

	/**
	 * @description: 商品详情
	 * @param: productId
	 * @return: java.lang.Object
	 */
	@GetMapping("details/{productId}")
	public Object getProduct(@PathVariable("productId") Long productId){
		return Result.ok(productService.getProductById(productId));
	}

	/**
	 * @description: 查询商品参数信息
	 * @param: productId
	 * @return: java.lang.Object
	 */
	@GetMapping("property/{productId}")
	public Object getProperty(@PathVariable("productId") Long productId){
		LambdaQueryWrapper<ProductProperty> wrapper = new QueryWrapper<ProductProperty>().lambda();
		wrapper.eq(ProductProperty::getProductId, productId);
		return Result.ok(productPropertyService.list(wrapper));
	}

	/**
	 * @description: 查询商品图片
	 * @param: productId
	 * @return: java.lang.Object
	 */
	@GetMapping("images/{productId}")
	public Object getImages(@PathVariable("productId") Long productId){
		LambdaQueryWrapper<ProductImage> wrapper = new QueryWrapper<ProductImage>().lambda();
		wrapper.eq(ProductImage::getProductId, productId);
		return Result.ok(productImageService.list(wrapper));
	}
}
