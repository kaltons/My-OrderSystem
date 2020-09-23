package com.kaltons.order.service.impl;

import com.kaltons.order.converter.OrderMasterToOrderDTOConverter;
import com.kaltons.order.dto.CartDTO;
import com.kaltons.order.dto.OrderDTO;
import com.kaltons.order.entity.OrderDetail;
import com.kaltons.order.entity.OrderMaster;
import com.kaltons.order.entity.ProductInfo;
import com.kaltons.order.enums.OrderStatusEnum;
import com.kaltons.order.enums.PayStatusEnum;
import com.kaltons.order.enums.ResultEnum;
import com.kaltons.order.exception.SellException;
import com.kaltons.order.repository.OrderDetailRepository;
import com.kaltons.order.repository.OrderMasterRepository;
import com.kaltons.order.service.OrderService;
import com.kaltons.order.service.ProductService;
import com.kaltons.order.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TODO
 * Order 业务操作实现类
 *
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 15:51
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional  //事务，错误时执行失败
    public OrderDTO create(OrderDTO orderDTO) {
        // 唯一订单号
        String orderId = KeyUtil.genUniqueKey();
        // 订单总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 查看商品数量
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            Optional<ProductInfo> productInfo = productService.findOne(orderDetail.getProductId());
            productInfo.orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXIST));

            // 计算订单总价
            orderAmount = productInfo.get().getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            // 订单详情写入数据库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo.get(), orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        // 写入订单数据库orderMaster
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        // 预扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }


    /**
     * 查看单个订单信息
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOne(String orderId) {

        //判断订单是否存在
        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderId);
        orderMaster.orElseThrow(() -> new SellException(ResultEnum.ORDER_NOT_EXIST));

        //判断订单详情是否存在
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        //返回订单数据
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster.get(), orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 根据用户微信openid查找订单列表
     *
     * @param opendId
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> findUserOeders(String opendId, Pageable pageable) {

        // 查找订单主表信息
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(opendId, pageable);

        //使用转换器获取拷贝的集合对象
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());

        //转换为返回对象
        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    /**
     * 取消订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);

        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        //加库存
        productService.increaseStock(cartDTOList);
        //如果已支付, 还需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDTO;
    }

    /**
     * 订单完成
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    /**
     * 支付订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO payOrder(OrderDTO orderDTO) {
        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付成功】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // 判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
