package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.ProductProperty;
import com.cuit.foodmall.entity.vo.ProductPropertyVO;
import com.cuit.foodmall.entity.vo.ProductVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author: YX
 * @date: 2020/3/3 08:52
 * @description:
 */
public interface ProductPropertyMapper extends BaseMapper<ProductProperty> {

	@Select("SELECT pp.*,p.name AS productName FROM product_property AS pp\n" +
			"LEFT JOIN product AS p ON p.id=pp.product_id ${ew.customSqlSegment}")
	IPage<ProductPropertyVO> listBySid(Page<ProductPropertyVO> ipage, @Param(Constants.WRAPPER) QueryWrapper<ProductVO> wrapper);
}
