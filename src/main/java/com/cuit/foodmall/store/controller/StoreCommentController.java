package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Review;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.vo.ReviewVO;
import com.cuit.foodmall.service.ReviewService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/18 09:48
 * @description: 商家评论管理
 */
@RestController
@RequestMapping("store/comment")
public class StoreCommentController {

	@Autowired
	private ReviewService reviewService;

	/**
	 * @description: 查询店铺留言
	 * @param: review
	 * @param: scoreTop
	 * @param: scoreButtom
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Review review, HttpSession session, Integer scoreTop, Integer scoreButtom,
	                   @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Long storeId = ((Store) session.getAttribute("store")).getId();//店铺ID
		Page<Review> ipage = new Page<>(page, limit);
		QueryWrapper<Review> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(review.getStatus())){
			wrapper.eq("r.status", review.getStatus());
		}
		if (null != scoreTop && null != scoreButtom){
			wrapper.between("r.score", scoreTop, scoreButtom);
		}
		wrapper.eq("r.store_id", storeId);
		wrapper.eq("r.del_flag", "0");
		IPage<ReviewVO> reviews = reviewService.listBySId(ipage, wrapper);
		return new Result(0,"",reviews.getTotal(),reviews.getRecords());
	}

	/**
	 * @description: 商家回复评价
	 * @param: review
	 * @return: java.lang.Object
	 */
	@PostMapping("reply")
	public Object reply(Review review, HttpSession session){
		review.setReplyUser(((User) session.getAttribute("business")).getUsername());//回复人
		UpdateWrapper<Review> wrapper = new UpdateWrapper<>();
		wrapper.eq("id", review.getId());
		wrapper.set("reply_user", review.getReplyUser());
		wrapper.set("reply_content", review.getReplyContent());
		wrapper.set("status", "1");
		if (reviewService.update(wrapper)){
			return Result.ok("回复成功");
		}
		return Result.error("回复失败");
	}
	/**
	 * @description: 删除留言
	 * @param: reviewId
	 * @return: java.lang.Object
	 */
	@PostMapping("del")
	public Object del(Long reviewId){
		if (reviewService.removeById(reviewId)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}
}
