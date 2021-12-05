package com.eshagibalov.cakesShop.orders;

import com.eshagibalov.cakesShop.rest.dto.order.Order;
import com.eshagibalov.cakesShop.rest.dto.order.OrderForAdmin;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);

    List<OrderForAdmin> getOrders();

    OrderForAdmin getOrderById(Long id);

    void changeStatus(Long id, OrderStatus orderStatus);
}
