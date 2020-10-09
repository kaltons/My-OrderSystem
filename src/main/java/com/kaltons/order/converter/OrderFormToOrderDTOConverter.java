package com.kaltons.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaltons.order.dto.OrderDTO;
import com.kaltons.order.entity.OrderDetail;
import com.kaltons.order.enums.ResultEnum;
import com.kaltons.order.exception.SellException;
import com.kaltons.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型转换器，将表单校验对象转化为数据传输对象
 *
 * @author 衍方
 * @desc
 * @date 2020/9/23 - 1:14
 */
@Slf4j
public class OrderFormToOrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){

        OrderDTO orderDTO = new OrderDTO();
        Gson gson = new Gson();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;

    }
}
