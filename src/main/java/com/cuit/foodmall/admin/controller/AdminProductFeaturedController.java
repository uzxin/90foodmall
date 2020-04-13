package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.ProductFeatured;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.ProductFeaturedService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author: YX
 * @date: 2020/4/3 10:32
 * @description: 商品推荐
 */
@RestController
@RequestMapping("admin/productFeatured")
public class AdminProductFeaturedController {

	@Autowired
	private ProductFeaturedService productFeaturedService;
	/**
	 * @description: 查询商品推荐
	 * @param: begin
	 * @param: end
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(String begin, String end, String position,
	                   @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<ProductFeatured> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<ProductFeatured> wrapper = new QueryWrapper<ProductFeatured>().lambda();
		if (StringUtils.isNotEmpty(begin) &&StringUtils.isNotEmpty(end)){
			wrapper.between(ProductFeatured::getStartDate, begin, end);
			wrapper.between(ProductFeatured::getEndDate, begin, end);
		}
		if (StringUtils.isNotEmpty(position)){
			wrapper.eq(ProductFeatured::getPosition, position);
		}
		wrapper.last("AND pf.del_flag=0 AND pi.type=0");
		IPage<ProductFeatured> p = productFeaturedService.listAll(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 检查商品推荐是否过期
	 * @return: java.lang.Object
	 */
	@GetMapping("check")
	public Object check(){
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		List<ProductFeatured> list = productFeaturedService.getAll(new QueryWrapper<ProductFeatured>().lambda().lt(ProductFeatured::getEndDate, now));
		if (list.isEmpty()){
			return Result.ok("检查完毕，没有过期数据");
		}
		for (ProductFeatured productFeatured : list) {
			productFeatured.setStatus("2");
		}
		if (productFeaturedService.updateBatchById(list)){
			return Result.ok("检查完毕，过期数据已重置");
		}
		return Result.error("检查失败");
	}

	/**
	 * @description: 添加
	 * @param: productFeatured
	 * @return: java.lang.Object
	 */
	@PostMapping("add")
	public Object add(ProductFeatured productFeatured, HttpSession session){
		LambdaQueryWrapper<ProductFeatured> wrapper = new QueryWrapper<ProductFeatured>().lambda();
		wrapper.eq(ProductFeatured::getProductId, productFeatured.getProductId());
		wrapper.eq(ProductFeatured::getPosition, productFeatured.getPosition());
		//wrapper.eq(pfget);
		if (!productFeaturedService.getAll(wrapper).isEmpty()){
			return Result.error("该商品已被推荐");
		}
		User user = (User) session.getAttribute("admin");
		productFeatured.setReferrerId(user.getId());
		productFeatured.setReferrerName(user.getUsername());
		if (productFeaturedService.save(productFeatured)){
			return Result.ok("添加成功");
		}
		return Result.error("添加失败");
	}

	/**
	 * @description: 删除
	 * @param: id
	 * @return: java.lang.Object
	 */
	@GetMapping("del")
	public Object del(Long id){
		if(productFeaturedService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}
}
