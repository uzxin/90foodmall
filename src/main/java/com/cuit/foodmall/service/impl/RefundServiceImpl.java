package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.Refund;
import com.cuit.foodmall.entity.vo.RefundVO;
import com.cuit.foodmall.mapper.RefundMapper;
import com.cuit.foodmall.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/12 17:06
 * @description:
 */
@Service("RefundService")
public class RefundServiceImpl extends ServiceImpl<RefundMapper, Refund> implements RefundService {

	@Autowired
	private RefundMapper refundMapper;

	@Override
	public List<RefundVO> listByUId(Long userId) {
		return refundMapper.listByUId(userId);
	}
}
