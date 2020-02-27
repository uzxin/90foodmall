package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuit.foodmall.entity.UserAddress;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author: YX
 * @date: 2020/2/26 15:42
 * @description:
 */
public interface UserAddressMapper extends BaseMapper<UserAddress> {

	@Update("update user_address set default_flag = case when id=#{id} then '1' else '0' end")
	public void setDefault(@Param("id") Long id);
}
