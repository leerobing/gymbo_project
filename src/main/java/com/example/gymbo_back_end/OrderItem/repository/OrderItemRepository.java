package com.example.gymbo_back_end.OrderItem.repository;

import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.core.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    @Query("SELECT ot FROM OrderItem ot WHERE ot.order = :order")
    List<OrderItem> findOrderItemsByOrder(@Param("order") Order order);

 }
