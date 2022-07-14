package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentFeignHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentFeignHystrixService {
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "------PaymentFallbackService fall back-paymentInfo,噢噢噢噢";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "------PaymentFallbackService fall paymentInfo_TimeOut,噢噢噢噢";
    }
}
