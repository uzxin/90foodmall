package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: YX
 * @date: 2020/2/27 14:28
 * @description: 银行
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("carousel")
public class Carousel extends BasePO{

    /*
    图片地址
     */
    @TableField("image_src")
    private String imageSrc;
    /*
    url地址
     */
    @TableField("url")
    private String url;
    /*
    备注
     */
    @TableField("remark")
    private String remark;
    /*
	创建人ID
	 */
    @TableField("create_user_id")
    private Long createUserId;
    /*
    创建人姓名
     */
    @TableField("create_user_name")
    private String createUserName;
    /*
	创建时间
	 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

}
