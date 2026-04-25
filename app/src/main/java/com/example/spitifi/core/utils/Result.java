package com.example.spitifi.core.utils;

public class Result<T> {
    private boolean success;
    private T data;
    private String message;

    public Result(String message, T data, boolean success) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public static <T> Result<T> success(T data){
        return new Result<>(null, data, true);
    }

    public static <T> Result<T> error (String message){
        return new Result<>(message, null, false);
    }
    public boolean isSuccess(){
        return success;
    }
    public T getData(){
        return data;
    }
    public String getMessage(){
        return message;
    }
}
