package com.kob.backend.controller.user.order;

import com.kob.backend.pojo.Order;
import com.kob.backend.pojo.OrderItem;
import com.kob.backend.pojo.OrderRequest;
import com.kob.backend.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public List<Order> getOrderList() {
        return orderService.getOrderList();
    }

    @PostMapping("/create")
    public Map<String, String> createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @PostMapping("/update")
    public Map<String, String> updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @PostMapping("/confirm/{id}")
    public Map<String, String> confirmOrder(@PathVariable BigInteger id) {
        return orderService.confirmOrder(id);
    }

    @PostMapping("/receive/{id}")
    public Map<String, String> receiveOrder(@PathVariable BigInteger id) {
        return orderService.receive(id);
    }

    @PostMapping("/ship/{id}")
    public Map<String, String> shipOrder(@PathVariable BigInteger id) {
        return orderService.shipOrder(id);
    }

    @PostMapping("/refund/{id}")
    public Map<String, String> refundOrder(@PathVariable BigInteger id) {
        return orderService.requestRefund(id);
    }

    @PostMapping("/refund/approve/{id}")
    public Map<String, String> approveRefund(@PathVariable BigInteger id) {
        return orderService.approveRefund(id);
    }

    @PostMapping("/refund/reject/{id}")
    public Map<String, String> rejectRefund(@PathVariable BigInteger id, @RequestParam String reason) {
        return orderService.rejectRefund(id, reason);
    }
} 