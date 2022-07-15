package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"ff8080816cd8c4d7016cd8f329500014"));
        hashMap.put(2L,new Payment(2L,"ff8080816cd8172b016cd817d5260000"));
        hashMap.put(3L,new Payment(3L,"ff8080816dd730c9016dd787c9c20001"));
    }
    @GetMapping(value="/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,"from mysql,serverPort: "+serverPort,payment);
        return result;

    }
}
