package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Product;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/13 14:55
 * @description: 商家提交商品审核请求
 */
@RestController
@RequestMapping("store/review")
public class StoreReviewController {

	@Autowired
	private ProductService productService;

	/**
	 * @description: 查询审核未通过的商品
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(@RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit,
	                   HttpSession session){
		Page<ProductVO> ipage = new Page<>(page, limit);
		Long storeId = ((Store) session.getAttribute("store")).getId();
		QueryWrapper<ProductVO> wrapper = new QueryWrapper<>();
		wrapper.eq("p.del_flag","0");
		wrapper.eq("p.store_id",storeId);
		wrapper.eq("p.status","12");
		IPage<ProductVO> p = productService.listProductBySid(ipage,wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 提交审核
	 * @param: product
	 * @return: java.lang.Object
	 */
	@PostMapping("submit")
	public Object review(Product product){
		//将产品状态置为待审核
		product.setStatus(10L);
		productService.updateById(product);
		return Result.ok("提交成功");
	}
}
