package com.cuit.foodmall.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author: YX
 * @date: 2020/2/17 15:09
 * @description: 返回数据格式
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Result {

    /**
     * 返回码
     **/
    private int code = 0;
    /**
     * 返回说明
     **/
    private String msg = "";
    /**
     * 条数
     **/
    private long count = 0;
    /**
     * 返回数据
     **/
    private Object data;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date time = new Date();


    public Result() {
    }

    public Result(Object data) {
        this(0, "操作成功", data);
    }

    public Result(int code, String msg) {
        this(code, msg, null);
    }

    public Result(int code, String msg, Object data) {
        this(code, msg, 0, data);
    }

    public Result(int code, String msg, long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static Result error() {
        return new Result(1, "操作失败", null);
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result error(String msg) {
        return error(1, msg);
    }


    public static Result ok() {
        return new Result(null);
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result ok(String msg) {
        return new Result(0, msg);
    }

    public static Result ok(String msg, Object data) {
        return new Result(0, msg, data);
    }

}
