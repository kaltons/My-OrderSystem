package com.kaltons.order.service.impl;

import com.kaltons.order.dto.OrderDTO;
import com.kaltons.order.entity.OrderDetail;
import com.kaltons.order.enums.OrderStatusEnum;
import com.kaltons.order.enums.PayStatusEnum;
import com.kaltons.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * Order —— 业务操作测试类
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 17:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID = "1008611";

    private final String ORDER_ID = "1600768959879303225";

    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("Kalton");
        orderDTO.setBuyerAddress("中国南海一小岛");
        orderDTO.setBuyerPhone("14725836988");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123458");
        o2.setProductQuantity(1);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO dto = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}", dto);
        Assert.assertEquals(ORDER_ID, dto.getOrderId());
    }

    @Test
    public void findUserOeders() {

        PageRequest request = PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findUserOeders(BUYER_OPENID, request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {

        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void payOrder() {

        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.payOrder(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());

    }
}