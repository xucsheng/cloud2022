package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentFeignHystrixService paymentFeignHystrixService;


    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public  String paymentInfo_Ok(@PathVariable("id") Integer id){
        String result = paymentFeignHystrixService.paymentInfo_Ok(id);
        log.info("************result:"+result);
        return result;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeOut/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_timeOutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })0
    @HystrixCommand
    public  String paymentInfo_Timeout(@PathVariable("id") Integer id){
        int age =10/0;
        String result = paymentFeignHystrixService.paymentInfo_TimeOut(id);
        log.info("************result:"+result);
        return result;
    }


    public  String paymentInfo_timeOutHandler(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试试或者自己运行请检查自己，00000";
    }
    // 下面是全局fallback
    public  String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，00000";
    }



}
