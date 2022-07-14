package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果："+result);

        if(result > 0){
            return  new CommonResult(200,"插入数据库成功,serverPort+"+serverPort,result);
        }else{
            return  new CommonResult(444,"插入数据库失败",null);
        }

    }

    @GetMapping("get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable  Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("插入结果："+payment+"\t"+"哈哈哈");
        if(payment != null){
            return  new CommonResult(200,"查询数据库成功serverPort="+serverPort,payment);
        }else{
            return  new CommonResult(444,"查询数据库失败:id="+id,null);
        }

    }
    @GetMapping("discovery")
    public Object discovery(){
       List<String> services = discoveryClient.getServices();
        services.forEach(item->log.info("******"+item));
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        for (ServiceInstance instance:  instances) {
            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
      return this.discoveryClient;
    }
    @GetMapping("lb")
    public String getPaymentLB(){
        return serverPort;
    }


    @GetMapping(value="feign/timeout")
    public String paymentFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return  serverPort;
    }

    @GetMapping(value="/zipkin")
    public String paymentZipkin(){

        return  "hi.i'am paymentzipkin server fall back,welcome tp atguigu,O(∩_∩)O哈哈~";
    }
}
