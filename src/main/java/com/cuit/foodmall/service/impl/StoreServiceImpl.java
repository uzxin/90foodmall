package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.mapper.StoreMapper;
import com.cuit.foodmall.service.StoreService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/9 11:08
 * @description:
 */
@Service("StoreService")
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {
}
