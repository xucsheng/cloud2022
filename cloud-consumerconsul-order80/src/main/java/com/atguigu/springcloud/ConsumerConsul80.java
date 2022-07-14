package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerConsul80 {
    public static void main(String[] args){
        SpringApplication.run(ConsumerConsul80.class,args);
        System.out.println("启动成功:80");
    }
}
