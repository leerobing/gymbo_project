package com.example.gymbo_back_end.order.repository;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {


    @Query("SELECT o FROM Order o WHERE o.member = :member")
    List<Order> findOrdersByMember(@Param("member") Member member);
}
