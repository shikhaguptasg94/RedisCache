package com.sheduler.sheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private int errorCode;
    private String errorMessage;
    private Object data;
    private boolean isSuccess;
}
