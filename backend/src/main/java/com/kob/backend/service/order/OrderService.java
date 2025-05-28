package com.kob.backend.service.order;

import com.kob.backend.pojo.Order;
import com.kob.backend.pojo.OrderItem;
import com.kob.backend.pojo.OrderRequest;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getOrderList();
    Map<String, String> createOrder(OrderRequest request);
    Map<String, String> updateOrder(Order order);
    Map<String, String> confirmOrder(BigInteger id);
    Map<String, String> shipOrder(BigInteger id);
    Map<String, String> requestRefund(BigInteger id);
    Map<String, String> approveRefund(BigInteger id);
    Map<String, String> rejectRefund(BigInteger id, String reason);
    Map<String, String> receive(BigInteger id);
} 