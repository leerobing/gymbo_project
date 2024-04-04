package com.example.gymbo_back_end.OrderItem.service;

import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.core.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> findOrderItemsByOrder(Long orderSeq);
}
