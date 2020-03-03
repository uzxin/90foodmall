package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Product;
import com.cuit.foodmall.entity.vo.ProductVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/2 11:16
 * @description:
 */
public interface ProductMapper extends BaseMapper<Product> {

	@Select("SELECT p.*,pi.src FROM product AS p " +
			"LEFT JOIN product_image AS pi " +
			"ON p.id=pi.product_id WHERE p.category_id=#{categoryId} AND pi.type=0")
	IPage<ProductVO> listProduct(Page<ProductVO> page, Long categoryId);

	@Select("SELECT p.*,pi.src FROM product AS p " +
			"INNER JOIN product_image AS pi " +
			"ON p.id=pi.product_id AND p.id=#{productId} AND pi.type=0")
	ProductVO getProductById(Long productId);
}
