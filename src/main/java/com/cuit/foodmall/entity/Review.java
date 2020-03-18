package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author: YX
 * @date: 2020/3/12 16:50
 * @description: 评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("review")
public class Review extends BasePO{

  /*
  订单ID
   */
  @TableField("order_id")
  private long orderId;
  /*
  店铺ID
   */
  @TableField("store_id")
  private long storeId;
  /*
  产品ID
   */
  @TableField("product_id")
  private long productId;
  /*
  产品名字
   */
  @TableField("product_name")
  private String productName;
  /*
  评论人ID
   */
  @TableField("commentator_id")
  private long commentatorId;
  /*
  评论人账号
   */
  @TableField("commentator_name")
  private String commentatorName;
  /*
  评论内容
   */
  @TableField("content")
  private String content;
  /*
  回复人
   */
  @TableField("reply_user")
  private String replyUser;
  /*
  回复内容
   */
  @TableField("reply_content")
  private String replyContent;
  /*
  状态
   */
  @TableField("status")
  private String status;
  /*
  创建时间
   */
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @TableField("create_time")
  private Date createTime;


}
