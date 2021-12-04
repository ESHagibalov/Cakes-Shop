package com.eshagibalov.cakesShop.orders;

import com.eshagibalov.cakesShop.goods.CakeEntity;

public interface PurchaseService {
    void addPurchase(OrderEntity orderEntity, CakeEntity cakeEntity, Integer count);
}
