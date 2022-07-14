package com.atguigu.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;



@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {
    public  static final String INVOKE_URL ="http://consul-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consul/zk")
    public String paymentInfo(){
        return restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
    }


}
