package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: YX
 * @date: 2020/2/17 15:15
 * @description:
 */
@Data
public abstract class BasePO implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 逻辑删除标志
     */
    @TableLogic
    private String delFlag;
}
