package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.Category;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.CategoryService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: YX
 * @date: 2020/3/24 14:16
 * @description:
 */
@RestController
@RequestMapping("admin/category")
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * @description: 查询所有分类
	 * @return: java.lang.Object
	 */
	@GetMapping("list")
	public Object list(){
		return Result.ok(categoryService.list());
	}

	/**
	 * @description: 删除分类
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("del")
	public Object del(Long id){
		if (categoryService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}

	/**
	 * @description: 添加或修改分类
	 * @param: category
	 * @return: java.lang.Object
	 */
	@PostMapping("addOrUpdate")
	public Object addOrUpdate(Category category, HttpSession session){
		if (null == category.getId()){
			User user = (User) session.getAttribute("admin");
			category.setCreateUserId(user.getId());
			category.setCreateUserName(user.getUsername());
		}
		if (categoryService.saveOrUpdate(category)){
			//更新redis
			List<Map<Category,List<Map<Category, List<Category>>>>> list = new ArrayList<>();
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
			return Result.ok();
		}
		return Result.error();
	}

	/**
	 * @description: 查询父节点下的分类
	 * @param: pid
	 * @return: java.util.List<com.cuit.foodmall.entity.Category>
	 */
	public List<Category> listByPid(Long pid){
		LambdaQueryWrapper<Category> wrapper = new QueryWrapper<Category>().lambda();
		wrapper.eq(Category::getPid, pid);
		return categoryService.list(wrapper);
	}

	/**
	 * @description: 查询某一级别下的分类
	 * @param: level
	 * @return: java.util.List<com.cuit.foodmall.entity.Category>
	 */
	public List<Category> listByLevel(Long level){
		LambdaQueryWrapper<Category> wrapper = new QueryWrapper<Category>().lambda();
		wrapper.eq(Category::getLevel, level);
		return categoryService.list(wrapper);
	}
}
