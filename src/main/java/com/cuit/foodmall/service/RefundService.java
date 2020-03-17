package com.cuit.foodmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.foodmall.entity.Refund;
import com.cuit.foodmall.entity.vo.RefundVO;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/12 17:06
 * @description:
 */
public interface RefundService extends IService<Refund> {

	/**
	 * @description: 查询用户退款信息
	 * @param: userId
	 * @return: java.util.List<com.cuit.foodmall.entity.vo.RefundVO>
	 */
	List<RefundVO> listByUId(Long userId);
}
