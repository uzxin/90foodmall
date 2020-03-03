package com.cuit.foodmall.user.controller;

import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/2 17:49
 * @description: 购物车
 */
@RestController
@RequestMapping("shoppingcart")
public class ShoppingCartController extends BaseController {

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private ProductService productService;

	/**
	 * @description: 获取购物车数据
	 * @return: java.lang.Object
	 */
	@GetMapping("list")
	public Object list(HttpSession session){
		String username = getUser(session).getUsername();
		List<Long> ids = (List<Long>) redisTemplate.opsForValue().get("shoppingcart:" + username);
		return Result.ok(productService.listByIds(ids));
	}

	/**
	 * @description: 添加购物车
	 * @param: productId
	 * @return: java.lang.Object
	 */
	@PostMapping("add")
	public Object add(Long productId, HttpSession session){
		String username = getUser(session).getUsername();
		if (redisTemplate.hasKey("shoppingcart:" + username)) {
			// 已经存在购物车数据
			List<Long> products = (List<Long>) redisTemplate.opsForValue().get("shoppingcart:" + username);
			products.add(productId);
			redisTemplate.opsForValue().set("shoppingcart:" + username, products);
		}else {
			// 新建购物车数据
			List<Long> list = new ArrayList<>();
			list.add(productId);
			redisTemplate.opsForValue().set("shoppingcart:" + username, list);
		}
		return Result.ok("添加成功");
	}

	/**
	 * @description: 删除购物车商品
	 * @param: productId
	 * @return: java.lang.Object
	 */
	@PostMapping("del")
	public Object del(Long productId, HttpSession session){
		String username = getUser(session).getUsername();
		List<Long> products = (List<Long>) redisTemplate.opsForValue().get("shoppingcart:" + username);
		assert products != null;
		Iterator<Long> iterator = products.iterator();
		while (iterator.hasNext()){
			Long pid = iterator.next();
			if (pid.equals(productId)){
				iterator.remove();
			}
		}
		redisTemplate.opsForValue().set("shoppingcart:" + username, products);
		return Result.ok("删除成功");
	}

}
