package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {


    @GetMapping("/testA")
    public  String testA(){
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return  "--------------testA";
    }

    @GetMapping("/testB")
    public  String testB(){
        log.info(Thread.currentThread().getName()+"\t"+"....testB");
        return  "--------------testB";
    }

    @GetMapping("/testD")
    public  String testD(){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("testD 测试RT");
        log.info("tesD 异常比例");
        int age=10/0;
        return  "--------------testD";
    }
    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey",blockHandler ="deal_testHotkey" )
    public String testHotkey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2
                             ){
        int age=10/0;
        return "**********************testHotkey";
    }

    public  String  deal_testHotkey(String p1, String p2, BlockException exception){
       return "--------------------testHotkey,o(╥﹏╥)o~";//sentinel 系统默认的提示：Blocked by Sentinel（flow limiting）
    }
}
