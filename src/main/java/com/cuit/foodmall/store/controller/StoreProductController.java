package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Product;
import com.cuit.foodmall.entity.ProductImage;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.service.ProductImageService;
import com.cuit.foodmall.service.ProductService;
import com.cuit.foodmall.user.controller.BaseController;
import com.cuit.foodmall.util.Result;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/10 11:06
 * @description: 商品添加
 */
@RestController
@RequestMapping("store/product")
public class StoreProductController extends BaseController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;

	/**
	 * @description: 添加商品
	 * @param: product
	 * @return: java.lang.Object
	 */
	@PostMapping("add")
	public Object add(Product product, HttpSession session){
		if (null != productService.getOne(new QueryWrapper<Product>().lambda().eq(Product::getName,product.getName()))){
			return Result.error("已存在该商品数据");
		}
		product.setStoreId(((Store)session.getAttribute("store")).getId());//店铺ID
		product.setCreateUserId(((User)session.getAttribute("business")).getId());//创建人ID
		product.setCreateUserName(((User)session.getAttribute("business")).getUsername());//创建人名字
		productService.save(product);//保存商品数据
		//返回插入数据的ID（需优化）
		LambdaQueryWrapper<Product> wrapper = new QueryWrapper<Product>().lambda();
		wrapper.eq(Product::getName,product.getName());
		wrapper.eq(Product::getCategoryId,product.getCategoryId());
		wrapper.eq(Product::getPriceCost,product.getPriceCost());
		wrapper.eq(Product::getInventory,product.getInventory());
		wrapper.eq(Product::getPriceOriginal,product.getPriceOriginal());
		wrapper.eq(Product::getPriceSale,product.getPriceSale());
		return Result.ok(productService.getOne(wrapper));
	}

	/**
	 * @description: 添加商品图片
	 * @param: images
	 * @param: productId
	 * @return: java.lang.Object
	 */
	@PostMapping("addImages")
	public Object addImages(String[] images, Long productId, HttpSession session){
		List<ProductImage> list = new ArrayList<>();
		for (String image : images) {
			ProductImage p = new ProductImage();
			p.setProductId(productId);//商品ID
			p.setSrc(image);//图片
			p.setCreateUserId(((User)session.getAttribute("business")).getId());//创建人ID
			p.setCreateUserName(((User)session.getAttribute("business")).getUsername());//创建人名字
			list.add(p);
		}
		productImageService.saveBatch(list);
		return Result.ok("添加成功");
	}

	/**
	 * @description: 查询店铺所有商品
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object list(Product product,String begin,String end,
	                   @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit,
	                   HttpSession session){
		Page<ProductVO> ipage = new Page<>(page, limit);
		Long storeId = ((Store) session.getAttribute("store")).getId();
		QueryWrapper<ProductVO> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(product.getName())){
			wrapper.like("p.name",product.getName());
		}
		if (StringUtils.isNotEmpty(begin) && StringUtils.isNotEmpty(end)){
			wrapper.between("p.create_time",begin,end);
		}
		wrapper.eq("p.del_flag","0");
		wrapper.eq("p.store_id",storeId);
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

	@PostMapping("update")
	public Object update(Product product){
		if (productService.updateById(product)){
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}
}
