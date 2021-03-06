package com.kaltons.order.service.impl;

import com.kaltons.order.dto.OrderDTO;
import com.kaltons.order.service.OrderService;
import com.kaltons.order.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/24 - 15:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void  create(){
        OrderDTO orderDTO = orderService.findOne("123456789");
        payService.create(orderDTO);
    }
}