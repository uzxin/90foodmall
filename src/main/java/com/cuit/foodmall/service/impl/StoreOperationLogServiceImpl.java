package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.StoreOperationLog;
import com.cuit.foodmall.mapper.StoreOperationLogMapper;
import com.cuit.foodmall.service.StoreOperationLogService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/3/31 11:23
 * @description:
 */
@Service("StoreOperationLogService")
public class StoreOperationLogServiceImpl extends ServiceImpl<StoreOperationLogMapper, StoreOperationLog> implements StoreOperationLogService {
}
