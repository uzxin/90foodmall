package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Product;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: YX
 * @date: 2020/3/24 16:18
 * @description:
 */
@RestController
@RequestMapping("admin/product")
public class AdminProductController {

	@Autowired
	private ProductService productService;

	/**
	 * @description: 查询所有商品
	 * @param: product
	 * @param: begin
	 * @param: end
	 * @param: sort
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Product product, String begin, String end, String sort,
	                   @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<ProductVO> ipage = new Page<>(page, limit);
		QueryWrapper<ProductVO> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(product.getName())){
			wrapper.like("p.name", product.getName());
		}
		if (StringUtils.isNotEmpty(begin) &&StringUtils.isNotEmpty(end)){
			wrapper.between("p.create_time", begin, end);
		}
		wrapper.eq("p.status","11");//只查询审核通过的商品
		wrapper.eq("p.del_flag","0");
		if (StringUtils.isNotEmpty(sort)){
			wrapper.orderByDesc("p."+sort);
		}
		IPage<ProductVO> p = productService.listProductBySid(ipage,wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 修改产品上下架状态
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("changeEnabled")
	public Object changeEnabled(Long id){
		Product product = productService.getById(id);
		if ("0".equals(product.getEnabled())) {
			product.setEnabled("1");
		} else {
			product.setEnabled("0");
		}
		if (productService.updateById(product)) {
			return Result.ok("修改成功");
		}
		return Result.ok("修改失败");
	}

	/**
	 * @description: 删除商品
	 * @param: id
	 * @return: java.lang.Object
	 */
	@GetMapping("del")
	public Object del(Long id){
		if(productService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}

}
