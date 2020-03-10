package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.Category;
import com.cuit.foodmall.service.CategoryService;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: YX
 * @date: 2020/2/28 09:07
 * @description: 商品分类
 */
@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * @description: 查询商品分类
	 * @return: java.lang.Object
	 */
	@GetMapping("listCategory")
	public Object listCategory(){
		// 从缓存中取
		Object category = redisTemplate.opsForValue().get("category");
		if (category != null){
			return Result.ok(category);
		}
		// 从数据库中查
		// 最终数据list<map>
		List<Map<Category,List<Map<Category,List<Category>>>>> list = new ArrayList<>();

		List<Category> categories1 = listByLevel(1L);//一级分类
		for (Category c1 : categories1) {
			Map<Category,List<Map<Category,List<Category>>>> map1 = new HashMap<>();
			List<Category> categories2 = listByPid(c1.getId());//二级分类
			List<Map<Category,List<Category>>> list2 = new ArrayList<>();
			for (Category c2 : categories2) {
				List<Category> categories3 = listByPid(c2.getId());//三级分类
				Map<Category,List<Category>> map3 = new HashMap<>();
				map3.put(c2, categories3);
				list2.add(map3);
			}
			map1.put(c1, list2);
			list.add(map1);
		}
		//存入redis
		redisTemplate.opsForValue().set("category", list);
		return Result.ok(list);
	}

	@GetMapping("list")
	public Object list(){
		return Result.ok(categoryService.list());
	}


	public List<Category> listByPid(Long pid){
		LambdaQueryWrapper<Category> wrapper = new QueryWrapper<Category>().lambda();
		wrapper.eq(Category::getPid, pid);
		return categoryService.list(wrapper);
	}

	public List<Category> listByLevel(Long level){
		LambdaQueryWrapper<Category> wrapper = new QueryWrapper<Category>().lambda();
		wrapper.eq(Category::getLevel, level);
		return categoryService.list(wrapper);
	}
}
