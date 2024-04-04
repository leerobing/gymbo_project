package com.example.gymbo_back_end.OrderItem.dao;

import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.core.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {

    List<OrderItem> findOrderItemsByOrder(Order order);
}
