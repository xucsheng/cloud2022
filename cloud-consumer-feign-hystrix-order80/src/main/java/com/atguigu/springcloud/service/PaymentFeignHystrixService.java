package com.atguigu.springcloud.service;


import com.atguigu.springcloud.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentFeignHystrixService {


    @GetMapping(value = "/payment/hystrix/ok/{id}")
    String paymentInfo_Ok(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeOut/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);

}
