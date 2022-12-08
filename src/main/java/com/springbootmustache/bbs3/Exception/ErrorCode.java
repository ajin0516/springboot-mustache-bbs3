package com.springbootmustache.bbs3.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "userName은 중복입니다"),
    NOT_FOUND(HttpStatus.NOT_FOUND,"Not Found"),
    INVALID_PASSWORD(HttpStatus.BAD_GATEWAY,"");

    private HttpStatus httpStatus;
    private String message;
}
