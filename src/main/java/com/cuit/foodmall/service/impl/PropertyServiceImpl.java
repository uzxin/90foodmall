package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Property;
import com.cuit.foodmall.mapper.PropertyMapper;
import com.cuit.foodmall.service.PropertyService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/3 08:54
 * @description:
 */
@Service("PropertyService")
public class PropertyServiceImpl extends ServiceImpl<PropertyMapper, Property> implements PropertyService {
}
