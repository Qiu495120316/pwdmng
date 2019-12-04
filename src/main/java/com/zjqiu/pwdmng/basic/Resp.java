package com.zjqiu.pwdmng.basic;

public class Resp<T> {

    public final static  Integer SUCCESS = 101;
    public final static Integer ERROR = 102;
    public final static  Integer LOGIN_SUCCESS = 100;
    public final static  Integer LOGIN_ERROR = 100;

    private Integer status;
    private String message;
    private T data;

    public static Resp create(){
        return new Resp();
    }

    public  Resp success( String message , T data ){
        this.status = SUCCESS;
        this.message = message;
        this.data = data;
        return this;
    }

    public  Resp error( String message , T data  ){
        this.status = ERROR;
        this.message = message;
        this.data = data;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
