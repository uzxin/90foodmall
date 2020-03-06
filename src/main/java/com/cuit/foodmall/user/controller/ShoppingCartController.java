package com.cuit.foodmall.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

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
		Map<ProductVO, Integer> shoppingCarts = (Map<ProductVO, Integer>) redisTemplate.opsForValue().get("shoppingcart:" + username);
		return Result.ok(shoppingCarts);
	}

	/**
	 * @description: 添加购物车
	 * @param: productId 商品ID
	 * @param: num 数量
	 * @return: java.lang.Object
	 */
	@PostMapping("add")
	public Object add(Long productId, @RequestParam(required = false,defaultValue = "1") int num, HttpSession session){
		String username = getUser(session).getUsername();
		ProductVO p = productService.getProductById(productId);
		if (redisTemplate.hasKey("shoppingcart:" + username)) {
			// 已经存在购物车数据
			Map<ProductVO, Integer> products = (Map<ProductVO, Integer>) redisTemplate.opsForValue().get("shoppingcart:" + username);
			products.put(p ,num);
			redisTemplate.opsForValue().set("shoppingcart:" + username, products);
		}else {
			// 新建购物车数据
			Map<ProductVO, Integer> map = new HashMap<>();
			map.put(p ,num);
			redisTemplate.opsForValue().set("shoppingcart:" + username, map);
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
		Map<ProductVO, Integer> products = (Map<ProductVO, Integer>) redisTemplate.opsForValue().get("shoppingcart:" + username);
		Iterator<Map.Entry<ProductVO, Integer>> iterator = products.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry<ProductVO, Integer> next = iterator.next();
			//反序列化
			ProductVO p = JSONObject.parseObject(String.valueOf(next.getKey()), ProductVO.class);
			if (p.getId() == productId){
				iterator.remove();
			}
		}
		redisTemplate.opsForValue().set("shoppingcart:" + username, products);
		return Result.ok("删除成功");
	}

}
