package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.ProductImage;
import com.cuit.foodmall.mapper.ProductImageMapper;
import com.cuit.foodmall.service.ProductImageService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/2 11:19
 * @description:
 */
@Service("ProductImageService")
public class ProductImageServiceImpl extends ServiceImpl<ProductImageMapper, ProductImage> implements ProductImageService {
}
