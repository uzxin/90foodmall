package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.ProductProperty;
import com.cuit.foodmall.mapper.ProductPropertyMapper;
import com.cuit.foodmall.service.ProductPropertyService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/3 08:55
 * @description:
 */
@Service("ProductPropertyService")
public class ProductPropertyServiceImpl extends ServiceImpl<ProductPropertyMapper, ProductProperty> implements ProductPropertyService {
}
