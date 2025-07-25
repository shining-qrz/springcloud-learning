package edu.wust.qrz.common;

import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result ok(){
        return new Result();
    }

    public static Result ok(String msg, Object data){
        Result result = new Result();
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static Result fail(){
        Result result = new Result();
        result.code = 500;
        result.msg = "服务器异常";
        return result;
    }

    public static Result fail(Integer code, String msg){
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        return result;
    }
}
