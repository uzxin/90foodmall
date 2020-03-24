package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Product;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.service.StoreService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YX
 * @date: 2020/3/24 12:12
 * @description: 商品审核
 */
@RestController
@RequestMapping("admin/productReview")
public class AdminProductReviewController {

	@Autowired
	private ProductService productService;

	/**
	 * @description: 查询待审核和未通过的商品
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Product product,@RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<ProductVO> ipage = new Page<>(page, limit);
		QueryWrapper<ProductVO> wrapper = new QueryWrapper<>();
		if (null != product.getStatus()){
			wrapper.eq("p.status",product.getStatus());
		}else {
			wrapper.in("p.status","10","12");//10代表待审核,12代表审核未通过
		}
		wrapper.eq("p.del_flag","0");
		IPage<ProductVO> p = productService.listProductBySid(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 审核通过
	 * @param: id
	 * @return: java.lang.Object
	 */
	@GetMapping("pass")
	public Object pass(Long id){
		UpdateWrapper<Product> wrapper = new UpdateWrapper<>();
		wrapper.eq("id",id);
		wrapper.set("status","11");//商品状态从待审核置为通过
		if (productService.update(wrapper)){
			return Result.ok();
		}
		return Result.error();
	}

	/**
	 * @description: 审核不通过
	 * @param: id
	 * @param: reviewOpinion
	 * @return: java.lang.Object
	 */
	@GetMapping("nopass")
	public Object nopass(Long id, String reviewOpinion){
		UpdateWrapper<Product> wrapper = new UpdateWrapper<>();
		wrapper.eq("id",id);
		wrapper.set("status","12");//商品状态置为不通过
		wrapper.set("review_opinion", reviewOpinion);
		if (productService.update(wrapper)){
			return Result.ok();
		}
		return Result.error();
	}
}
