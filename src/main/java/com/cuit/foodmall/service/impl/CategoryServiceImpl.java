package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Category;
import com.cuit.foodmall.mapper.CategoryMapper;
import com.cuit.foodmall.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/2/28 09:06
 * @description:
 */
@Service("CategoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
