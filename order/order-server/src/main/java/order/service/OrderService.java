package order.service;

import order.dto.OrderDTO;

public interface OrderService {

    /**
     *  创建订单
     * */
    OrderDTO create(OrderDTO orderDTO);



}
