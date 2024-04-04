package com.example.gymbo_back_end.order.dao;

import com.example.gymbo_back_end.core.commom.exception.order.OrderNotFoundException;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderJpaDao implements OrderDao {

    private final OrderRepository orderRepository;
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOne(Long orderSeq) {
        Order order = orderRepository.findById(orderSeq).orElseThrow(()
                -> new OrderNotFoundException("존재하지 않는 주문입니다."));
        return order;
    }

    @Override
    public List<Order> findOrdersByMember(Member member) {
        return orderRepository.findOrdersByMember(member);
    }

    @Override
    public Optional<Order> findById(Long orderSeq) {
        return orderRepository.findById(orderSeq);
    }
}
