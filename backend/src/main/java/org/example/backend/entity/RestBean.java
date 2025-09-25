package org.example.backend.entity;


import lombok.Data;

@Data
public class RestBean<T> {
    private int status;
    private boolean success;
    private T data;

    public RestBean(int status, boolean success, T data){
        this.status = status;
        this.success = success;
        this.data = data;
    }

    public static <T> RestBean<T> success(T data){
        return new RestBean<>(200,true,data);
    }

    public static <T> RestBean<T> failure(int status,T data){
        return new RestBean<>(status,false,data);
    }

}
