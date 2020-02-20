package com.cuit.foodmall.user.controller;

import com.cuit.foodmall.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/2/20 11:29
 * @description:
 */
public class BaseController {

	public static User getUser(HttpSession session){
		return (User) session.getAttribute("user");
	}
}
