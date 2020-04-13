package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.ProductFeatured;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/4/3 10:29
 * @description:
 */
public interface ProductFeaturedMapper extends BaseMapper<ProductFeatured> {

	@Select("SELECT pf.*,p.name AS productName,p.sales AS sales,pi.src AS src,p.price_sale AS priceSale,c.name AS categoryName,s.name AS storeName\n" +
			"FROM product_featured AS pf\n" +
			"INNER JOIN product AS p ON p.id=pf.product_id\n" +
			"INNER JOIN product_image AS pi ON pi.product_id=pf.product_id\n" +
			"INNER JOIN category AS c ON c.id=p.category_id\n" +
			"INNER JOIN store AS s ON s.id=p.store_id ${ew.customSqlSegment}")
	IPage<ProductFeatured> listAll(Page<ProductFeatured> ipage, @Param(Constants.WRAPPER) LambdaQueryWrapper<ProductFeatured> wrapper);

	@Select("SELECT id,end_date FROM product_featured ${ew.customSqlSegment}")
	List<ProductFeatured> getAll(@Param(Constants.WRAPPER) LambdaQueryWrapper<ProductFeatured> wrapper);

	@Select("SELECT id FROM product_featured WHERE id=#{productId}")
	ProductFeatured getOneById(Long productId);
}
