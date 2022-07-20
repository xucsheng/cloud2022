package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao   orderDao;

    @Resource
    private StorageService storageService;


    @Resource
    private AccountService accountService;
    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 简单说：下订单->扣库存->减余额->改状态
     * 注释掉 @GlobalTransactional 的时候，需要注意下方这个方法里面手动模拟了延时，也需要注释掉
     * com.atguigu.springcloud.alibaba.service.impl.AccountServiceImpl#decrease(java.lang.Long, java.math.BigDecimal)
     */

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        // 1 创建订单
        log.info("----->开始创建订单");
        orderDao.create(order);
        log.info("----->订单微服务开始调用库存，做扣减count");
        //2  调用库存服务扣减库存
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用账号，做扣减Money");
        //3 调用账户服务扣减账户余额
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用库存，做扣减end");
        // 修改订单状态，从零开始1：代表已经完成
        log.info("----->修改订单状态开始");
        //4  修改订单状态
        orderDao.update(order.getUserId(),0);
        log.info("----->修改订单状态结束");
        log.info("---->下订单结束了，O(∩_∩)O哈哈~");

    }


}
