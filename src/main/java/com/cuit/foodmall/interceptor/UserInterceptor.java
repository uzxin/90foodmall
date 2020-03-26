package com.cuit.foodmall.interceptor;

import com.cuit.foodmall.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: YX
 * @date: 2020/3/2 17:44
 * @description: 拦截器
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
	//请求处理前，也就是访问Controller前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		if(null == user){
			if(null != request.getHeader("X-Requested-With")){
				//拦截ajax请求
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("请登录");
			}else {
				//重定向到登陆页面
				response.sendRedirect(request.getContextPath()+"/user/home/login.html");
			}
			return false;
		}else {
			return true;
		}
	}

	//请求已经被处理，但在视图渲染前
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	//请求结果结果已经渲染好了，response没有进行返回但也无法修改reponse了（一般用于清理数据）
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
