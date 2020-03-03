package com.cuit.foodmall.config;

import com.cuit.foodmall.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import java.io.FileNotFoundException;

/**
 * @author: YX
 * @date: 2020/2/20 10:42
 * @description: 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    //跨域设置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true).maxAge(3600);
    }

    //资源路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            registry.addResourceHandler("/upload/**","/**")
                    .addResourceLocations("classpath:/static/", "file:"+ ResourceUtils.getFile("classpath:").getParent()+"/upload/");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //试图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/user/home/index.html");
        registry.addViewController("/index").setViewName("redirect:/user/home/index.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 通过registry来注册拦截器，通过addPathPatterns来添加拦截路径
        registry.addInterceptor(this.myInterceptor).addPathPatterns("/shoppingcart/**");
    }
}
