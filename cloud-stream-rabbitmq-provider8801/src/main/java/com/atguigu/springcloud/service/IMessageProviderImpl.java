package com.atguigu.springcloud.service;
import cn.hutool.core.lang.UUID;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import javax.annotation.Resource;


@EnableBinding(Source.class)// 定义消息推送的管道
public class IMessageProviderImpl implements IMessageProvider{

    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial:"+serial);
        return null;
    }
}
