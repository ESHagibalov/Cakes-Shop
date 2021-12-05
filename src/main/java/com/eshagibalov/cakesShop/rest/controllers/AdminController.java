package com.eshagibalov.cakesShop.rest.controllers;

import com.eshagibalov.cakesShop.goods.CakeService;
import com.eshagibalov.cakesShop.orders.OrderService;
import com.eshagibalov.cakesShop.orders.OrderStatusData;
import com.eshagibalov.cakesShop.rest.dto.order.OrderForAdmin;
import com.eshagibalov.cakesShop.users.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/admin-panel")
public class AdminController {
    private OrderService orderService;
    private UserService userService;
    private CakeService cakeService;

    public AdminController(OrderService orderService, UserService userService, CakeService cakeService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cakeService = cakeService;
    }

    @GetMapping("/admin-menu")
    public ModelAndView getAdminMenu() {
        return new ModelAndView("menu");
    }

    @GetMapping("/admin-orders")
    public ModelAndView getAdminOrdersList() {
        ModelAndView modelAndView = new ModelAndView("orders");
        modelAndView.addObject("orders", orderService.getOrders());
        return modelAndView;
    }

    @GetMapping("/admin-cakes")
    public ModelAndView getAdminCakesList() {
        ModelAndView modelAndView = new ModelAndView("cakes");
        modelAndView.addObject("cakes", cakeService.getCakes().getCakeList());
        return modelAndView;
    }

    @GetMapping("/admin-users")
    public ModelAndView getAdminUsersList() {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", userService.getUsers());
        return modelAndView;
    }

    @GetMapping("/admin-get-order/{id}")
    public ModelAndView getAdminOrderById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("order");
        OrderForAdmin orderForAdmin = orderService.getOrderById(id);
        modelAndView.addObject("order", orderForAdmin);
        System.out.println(orderForAdmin);
        return modelAndView;
    }

    @PostMapping("/admin-get-order/{id}")
    public RedirectView editOrder(@PathVariable Long id, OrderStatusData orderStatusData) {
        orderService.changeStatus(id, orderStatusData.getStatus());
        return new RedirectView(String.format("/admin-panel/admin-get-order/%d", id));
    }

    @GetMapping("/admin-get-order/delete/{id}")
    public RedirectView deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return new RedirectView("/admin-panel/admin-orders");
    }
}
