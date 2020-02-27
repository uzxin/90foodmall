package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Address;
import com.cuit.foodmall.mapper.AddressMapper;
import com.cuit.foodmall.service.AddressService;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/2/26 15:43
 * @description:
 */
@Service("AddressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
}
