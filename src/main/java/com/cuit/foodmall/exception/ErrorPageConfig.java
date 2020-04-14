package com.cuit.foodmall.exception;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author: YX
 * @date: 2020/4/14 17:44
 * @description: 异常转发
 */
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/admin/page/error/error-403.html");
		ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/admin/page/error/error-404.html");
		ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/admin/page/error/error-500.html");
		registry.addErrorPages(error403Page,error404Page,error500Page);
	}
}