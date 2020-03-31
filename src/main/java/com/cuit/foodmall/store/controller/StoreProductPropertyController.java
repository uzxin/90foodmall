package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.aop.StoreLog;
import com.cuit.foodmall.entity.ProductProperty;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.vo.ProductPropertyVO;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.service.ProductPropertyService;
import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.service.PropertyService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/20 09:35
 * @description: 商品属性
 */
@RestController
@RequestMapping("store/productProperty")
public class StoreProductPropertyController {

	@Autowired
	private ProductPropertyService productPropertyService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PropertyService propertyService;

	/**
	 * @description: 查询店铺旗下产品属性
	 * @param: productProperty
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "查看商品属性")
	@GetMapping("page")
	public Object page(ProductProperty productProperty, HttpSession session,
	                   @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<ProductPropertyVO> ipage = new Page<>(page, limit);
		QueryWrapper<ProductVO> wrapper = new QueryWrapper<>();
		if (null != productProperty.getProductId()){
			wrapper.eq("pp.product_id", productProperty.getProductId());
		}
		if (null != productProperty.getPropertyId()){
			wrapper.eq("pp.property_id", productProperty.getPropertyId());
		}
		wrapper.eq("pp.del_flag","0");
		Long storeId = ((Store) session.getAttribute("store")).getId();
		wrapper.last("AND pp.product_id IN(select id from product where store_id="+storeId+")");
		IPage<ProductPropertyVO> p = productPropertyService.listBySid(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 增加
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "添加商品属性")
	@PostMapping("add")
	public Object add(ProductProperty productProperty, HttpSession session){
		//查询是否该商品已存在该属性
		LambdaQueryWrapper<ProductProperty> wrapper = new QueryWrapper<ProductProperty>().lambda();
		wrapper.eq(ProductProperty::getProductId, productProperty.getProductId());
		wrapper.eq(ProductProperty::getPropertyId, productProperty.getPropertyId());
		if (null != productPropertyService.getOne(wrapper)){
			return Result.error("已存在该属性");
		}
		User user = (User) session.getAttribute("business");
		productProperty.setCreateUserId(user.getId());
		productProperty.setCreateUserName(user.getUsername());
		productPropertyService.save(productProperty);
		return Result.ok("添加成功");
	}
	/**
	 * @description: 修改属性
	 * @param: productProperty
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "修改商品属性")
	@PostMapping("update")
	public Object update(ProductProperty productProperty){
		UpdateWrapper<ProductProperty> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", productProperty.getId());
		wrapper.set("property_value", productProperty.getPropertyValue());
		if (productPropertyService.update(wrapper)){
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}

	/**
	 * @description: 删除属性
	 * @param: id
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "删除商品属性")
	@PostMapping("del")
	public Object del(Long id){
		if (productPropertyService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}

	/**
	 * @description: 查询店铺旗下所有商品
	 * @return: java.lang.Object
	 */
	@GetMapping("listProduct")
	public Object listProduct(){
		return Result.ok(productService.list());
	}

	/**
	 * @description: 查询所有商品属性
	 * @return: java.lang.Object
	 */
	@GetMapping("listProductProperty")
	public Object listProductProperty(){
		return Result.ok(propertyService.list());
	}

}
