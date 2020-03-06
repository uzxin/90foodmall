package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.ShipMethod;
import com.cuit.foodmall.mapper.ShipMethodMapper;
import com.cuit.foodmall.service.ShipMethodService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/6 10:19
 * @description:
 */
@Service("ShipMethodService")
public class ShipMethodServiceImpl extends ServiceImpl<ShipMethodMapper, ShipMethod> implements ShipMethodService {
}
