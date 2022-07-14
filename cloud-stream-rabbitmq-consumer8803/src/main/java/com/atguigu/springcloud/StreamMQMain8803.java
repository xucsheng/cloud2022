package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMQMain8803 {
    public static void main( String[] args ) {
        SpringApplication.run(StreamMQMain8803.class,args);
    }
}
