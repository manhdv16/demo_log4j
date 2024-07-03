package com.dvm.demo_log4j.response;

import lombok.Data;

@Data
public class ApiResponse<T>{
    private int code;
    private int status;
    private String message;
    private T data;

    public ApiResponse(int code, int status, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.status = status;
    }

}
