package com.example.gymbo_back_end.gym.repository;

import com.example.gymbo_back_end.core.entity.Gym;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym,Long> {

    Optional<Gym> findByGymName(String gymName);

    Optional<Gym> findByGymNumber (String gymNumber);

    @Query("SELECT g FROM Gym g WHERE UPPER(g.gymName) LIKE UPPER(CONCAT('%', :keyword, '%'))")
    Slice<Gym> findByGymNameContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT g FROM Gym g WHERE UPPER(g.gymSports) LIKE UPPER(CONCAT('%', :keyword, '%'))")
    Slice<Gym> findByGymSportsContaining(@Param("keyword") String keyword, Pageable pageable);
}
