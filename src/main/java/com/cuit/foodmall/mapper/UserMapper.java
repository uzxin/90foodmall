package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Auth;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/2/17 15:32
 * @description:
 */
public interface UserMapper extends BaseMapper<User> {

	@Select("SELECT u.id AS id,u.user_username AS username,u.user_account_status AS accountStatus,u.create_time AS createTime,ui.user_nickname AS nickName,ui.user_gender AS gender,ur.role_id AS roleId,ur.role_name AS roleName FROM user AS u\n" +
			"LEFT JOIN user_information AS ui ON u.id=ui.user_id\n" +
			"LEFT JOIN user_role AS ur ON u.id=ur.user_id ${ew.customSqlSegment}")
	IPage<UserVO> listUser(Page<UserVO> ipage, @Param(Constants.WRAPPER) QueryWrapper<UserVO> wrapper);

	@Select("SELECT * FROM auth WHERE id in\n" +
			"(SELECT auth_id FROM role_auth WHERE role_id in\n" +
			"(SELECT role_id from user_role WHERE user_id=#{userId}))")
	List<Auth> getAuths(Long userId);
}
