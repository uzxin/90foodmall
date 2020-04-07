package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Carousel;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.CarouselService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/4/6 15:52
 * @description: 首页轮播图
 */
@RestController
@RequestMapping("admin/carousel")
public class AdminCarouselController {

    @Autowired
    private CarouselService carouselService;

    /**
     * @description: 查询轮播图
     * @param: page
     * @param: limit
     * @return: java.lang.Object
     */
    @GetMapping("page")
    public Object page(@RequestParam(required = false,defaultValue = "1") int page,
                       @RequestParam(required = false,defaultValue = "10") int limit){
        IPage<Carousel> p = carouselService.page(new Page<>(page, limit));
        return new Result(0,"",p.getTotal(),p.getRecords());
    }

    /**
     * @description: 更新或添加
     * @param: carousel
     * @return: java.lang.Object
     */
    @PostMapping("addOrUpdate")
    public Object addOrUpdate(Carousel carousel, HttpSession session){
        if (null == carousel.getId()){
            User user = (User) session.getAttribute("admin");
            carousel.setCreateUserId(user.getId());
            carousel.setCreateUserName(user.getUsername());
        }
        if (carouselService.saveOrUpdate(carousel)){
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * @description: 删除
     * @param: id
     * @return: java.lang.Object
     */
    @PostMapping("del")
    public Object del(Long id){
        if (carouselService.removeById(id)){
            return Result.ok("删除成功");
        }
        return Result.error("删除失败");
    }
}
