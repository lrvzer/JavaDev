package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderHystrixFallbackService implements OrderHystrixService {

    @Override
    public String paymentInfo_ok(Integer id) {
        return "----OrderHystrixFallbackService fall back paymentInfo_ok----";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----OrderHystrixFallbackService fall back paymentInfo_TimeOut----";
    }
}
