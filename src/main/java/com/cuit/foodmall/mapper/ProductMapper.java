package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Product;
import com.cuit.foodmall.entity.vo.ProductVO;
import org.apache.ibatis.annotations.Param;
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
			"ON p.id=pi.product_id WHERE p.category_id=#{categoryId} AND p.status=11 AND p.del_flag=0 AND pi.type=0")
	IPage<ProductVO> listProductByCid(Page<ProductVO> page, Long categoryId);

	@Select("SELECT p.*,pi.src FROM product AS p " +
			"INNER JOIN product_image AS pi " +
			"ON p.id=pi.product_id AND p.id=#{productId} AND p.del_flag=0 AND pi.type=0")
	ProductVO getProductById(Long productId);

	@Select("SELECT p.*,c.name AS categoryName,s.name AS storeName,status.name AS statusName FROM product AS p \n" +
			"LEFT JOIN category AS c ON p.category_id=c.id\n" +
			"LEFT JOIN store AS s ON p.store_id=s.id\n" +
			"LEFT JOIN status ON p.status=status.id ${ew.customSqlSegment}")
	IPage<ProductVO> listProductBySid(Page<ProductVO> page, @Param(Constants.WRAPPER) QueryWrapper<ProductVO> wrapper);
}
