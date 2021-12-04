package com.eshagibalov.cakesShop.orders;

import com.eshagibalov.cakesShop.goods.CakeRepository;
import com.eshagibalov.cakesShop.orders.*;
import com.eshagibalov.cakesShop.rest.dto.order.Order;
import com.eshagibalov.cakesShop.users.UserRepository;
import com.eshagibalov.cakesShop.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final CakeRepository cakeRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, PurchaseRepository purchaseRepository, UserService userService, UserRepository userRepository, CakeRepository cakeRepository) {
        this.orderRepository = orderRepository;
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.cakeRepository = cakeRepository;
    }

    public void addOrder(Order order) {

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setDeliveryMethod(order.getDeliveryMethod());
        orderEntity.setAddress(order.getAddress());
        orderEntity.setTime(order.getTime());
        orderEntity.setPaymentMethod(order.getPaymentMethod());
        orderEntity.setOrderStatus(order.getOrderStatus());
        orderEntity.setPurchases(order.getPurchases().stream()
                .map(purchase -> {
                    PurchaseEntity purchaseEntity = new PurchaseEntity();
                    purchaseEntity.setNumber(purchase.getNum());
                    purchaseEntity.setOrder(orderEntity);
                    purchaseEntity.setCake(cakeRepository.findById(purchase.getId()).orElseThrow(RuntimeException::new));
                    return purchaseEntity;
                }).collect(Collectors.toList()));
        orderEntity.setUser(userRepository.findUserEntityByNumber(order.getUser().getNumber()));
        orderRepository.saveAndFlush(orderEntity);
    }
}
