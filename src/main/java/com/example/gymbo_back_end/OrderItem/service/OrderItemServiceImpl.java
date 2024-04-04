package com.example.gymbo_back_end.OrderItem.service;

import com.example.gymbo_back_end.OrderItem.dao.OrderItemDao;
import com.example.gymbo_back_end.OrderItem.repository.OrderItemRepository;
import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.core.entity.OrderItem;
import com.example.gymbo_back_end.order.dao.OrderDao;
import com.example.gymbo_back_end.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OrderItemServiceImpl implements OrderItemService {


    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;

    @Override
    public List<OrderItem> findOrderItemsByOrder(Long orderSeq) {
        Order order = orderDao.findById(orderSeq).orElseThrow(() -> new EntityNotFoundException());
        List<OrderItem> byOrderSeq = orderItemDao.findOrderItemsByOrder(order);
        return byOrderSeq;
    }
}
