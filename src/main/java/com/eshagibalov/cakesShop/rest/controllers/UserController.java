package com.eshagibalov.cakesShop.rest.controllers;

import com.eshagibalov.cakesShop.exceptions.UserExistException;
import com.eshagibalov.cakesShop.goods.CakeService;
import com.eshagibalov.cakesShop.orders.OrderService;
import com.eshagibalov.cakesShop.orders.PurchaseService;
import com.eshagibalov.cakesShop.rest.dto.cake.Cake;
import com.eshagibalov.cakesShop.rest.dto.cake.CakeMoreInfo;
import com.eshagibalov.cakesShop.rest.dto.cake.Cakes;
import com.eshagibalov.cakesShop.rest.dto.order.Order;
import com.eshagibalov.cakesShop.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
//@RequestMapping ("v1/cakes")
public class UserController {
    private final UserService userService;
    private final CakeService cakeService;
    private final PurchaseService purchaseService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, PurchaseService purchaseService, OrderService orderService, CakeService cakeService) {
        this.userService = userService;
        this.cakeService = cakeService;
        this.purchaseService = purchaseService;
        this.orderService = orderService;

    }

    @GetMapping(value = "get-all-cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes() {
        return cakeService.getCakes();
    }

    @GetMapping(value = "cake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CakeMoreInfo getCakeById(@PathVariable Long id) {
        return cakeService.getMoreInfo(id);
    }

    @PostMapping(path = "add-cake", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cake> createCake(@RequestBody @Valid CakeMoreInfo cakeMoreInfo) {
        cakeService.addCake(cakeMoreInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "add-order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getOrCreateOrder(@RequestBody @Valid Order newOrder) {
        try {
            userService.addUser(newOrder.getUser());
        } catch (UserExistException userExistException) {
            orderService.addOrder(newOrder);
        }
    }

}