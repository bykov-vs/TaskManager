package com.coursework.TaskManager.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseResponse {
    public String status;
    public String message;

    public BaseResponse(String status) {
        this.status = status;
    }
}
