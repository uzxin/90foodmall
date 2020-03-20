package com.cuit.foodmall.store.controller;

import com.cuit.foodmall.service.CategoryService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YX
 * @date: 2020/3/19 13:51
 * @description: 分类
 */
@RestController
@RequestMapping("/store/category")
public class StoreCategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * @description: 查询商品所有分类
	 * @return: java.lang.Object
	 */
	@GetMapping("list")
	public Object list(){
		return Result.ok(categoryService.list());
	}
}
