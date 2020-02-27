package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cuit.foodmall.entity.Address;
import com.cuit.foodmall.entity.UserAddress;
import com.cuit.foodmall.entity.vo.UserAddressVO;
import com.cuit.foodmall.service.AddressService;
import com.cuit.foodmall.service.UserAddressService;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: YX
 * @date: 2020/2/26 15:30
 * @description: 收货地址
 */
@RestController
@RequestMapping("address")
@Slf4j
public class AddressController extends BaseController{

	@Autowired
	private AddressService addressService;
	@Autowired
	private UserAddressService userAddressService;

	/**
	 * @description: 查询所有收货地址
	 * @return: java.lang.Object
	 */
	@GetMapping("listAddress")
	public Object listAddress(HttpSession session){
		Long userId = getUser(session).getId();
		LambdaQueryWrapper<UserAddress> wrapper = new QueryWrapper<UserAddress>().lambda();
		wrapper.eq(UserAddress::getUserId, userId);
		wrapper.orderByDesc(UserAddress::getDefaultFlag);
		List<UserAddress> POList = userAddressService.list(wrapper);
		List<UserAddressVO> VOList = new ArrayList<>();
		for (UserAddress u : POList) {
			UserAddressVO vo = new UserAddressVO();
			BeanUtils.copyProperties(u, vo);
			//区县
			Address country = addressService.getById(u.getAddressId());
			vo.setCountry(country.getDistrict());
			//市
			Address city = addressService.getById(country.getPid());
			vo.setCity(city.getDistrict());
			//省
			Address province = addressService.getById(city.getPid());
			vo.setProvince(province.getDistrict());
			VOList.add(vo);
		}
		return Result.ok(VOList);
	}

	/**
	 * @description: 根据级别获取市区县
	 * @param: level 级别
	 * @return: java.lang.Object
	 */
	@GetMapping("getAddressByLevel")
	public Object getAddressByLevel(int level){
		LambdaQueryWrapper<Address> wrapper = new QueryWrapper<Address>().lambda();
		wrapper.eq(Address::getLevel, level);
		return Result.ok(addressService.list(wrapper));
	}

	/**
	 * @description: 根据父级地区获取子地区
	 * @param: pid 父级地区ID
	 * @return: java.lang.Object
	 */
	@GetMapping("getAddressByPid")
	public Object getAddressByPid(Long pid){
		LambdaQueryWrapper<Address> wrapper = new QueryWrapper<Address>().lambda();
		wrapper.eq(Address::getPid, pid);
		return Result.ok(addressService.list(wrapper));
	}

	/**
	 * @description: 添加收货地址
	 * @param: userAddress
	 * @return: java.lang.Object
	 */
	@PostMapping("save")
	public Object save(UserAddress userAddress, HttpSession session){
		userAddress.setUserId(getUser(session).getId());
		userAddressService.save(userAddress);
		return Result.ok("保存成功");
	}

	/**
	 * @description: 设置默认地址
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("setDefault")
	public Object setDefault(Long id){
		userAddressService.setDefault(id);
		return Result.ok("修改成功");
	}

	/**
	 * @description: 删除地址
	 * @param: id
	 * @return: java.lang.Object
	 */
	@GetMapping("del")
	public Object delete(Long id){
		if (userAddressService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.ok("删除失败");
	}

	/**
	 * @description: 修改收货地址
	 * @param: userAddress
	 * @return: java.lang.Object
	 */
	@PostMapping("update")
	public Object update(UserAddress userAddress){
		if (userAddressService.updateById(userAddress)){
			return Result.ok("修改成功");
		}
		return Result.ok("修改失败");
	}
}
