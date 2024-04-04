package com.example.gymbo_back_end.OrderItem.dao;

import com.example.gymbo_back_end.OrderItem.repository.OrderItemRepository;
import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.core.entity.OrderItem;
import com.example.gymbo_back_end.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderItemJpaDao implements OrderItemDao{

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findOrderItemsByOrder(Order order) {

        return orderItemRepository.findOrderItemsByOrder(order);
    }
}
