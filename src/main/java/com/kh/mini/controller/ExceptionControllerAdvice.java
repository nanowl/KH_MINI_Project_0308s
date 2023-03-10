package com.kh.mini.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        // 예외 처리 로직 작성
        return "error-page";
    }
}
