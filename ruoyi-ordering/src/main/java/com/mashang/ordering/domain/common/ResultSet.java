package com.mashang.ordering.domain.common;

import lombok.Data;

//部分逻辑放在服务层写了
//传这个对象回去直接拿值或者错误信息了……
@Data
public class ResultSet<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ResultSet<T> success(T data) {
        ResultSet<T> result = new ResultSet<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }
    public static <T> ResultSet<T> success(T data,String msg) {
        ResultSet<T> result = new ResultSet<>();
        result.setSuccess(true);
        result.setData(data);
        result.setMessage(msg);
        return result;
    }
    public static <T> ResultSet<T> fail(String message) {
        ResultSet<T> result = new ResultSet<>();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
}
