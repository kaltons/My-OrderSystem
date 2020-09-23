package com.kaltons.order.converter;

import com.kaltons.order.dto.OrderDTO;
import com.kaltons.order.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 * 类型转换器
 * @author 衍方
 * @desc 类型转换器
 * @date 2020/9/22 - 18:44
 */
public class OrderMasterToOrderDTOConverter {

    /**
     * 属性拷贝
     * @param orderMaster
     * @return
     */
    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    /**
     * 集合元素拷贝
     * @param orderMasterList
     * @return
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }

}
