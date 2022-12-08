package com.springbootmustache.bbs3.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HospitalReviewAppException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;


    @Override
    public String toString() {
        if (message == null) return errorCode.getMessage();
        return String.format("%s. %s", errorCode.getMessage(), message);

    }
}
