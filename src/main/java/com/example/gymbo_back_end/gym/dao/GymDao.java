package com.example.gymbo_back_end.gym.dao;

import com.example.gymbo_back_end.core.entity.Gym;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface GymDao {

    Gym save(Gym gym);

    Gym findByGymName(String gymName);

    Gym findByGymNumber(String gymNumber);

    List<Gym> findAll();

    Gym find(Long gymSeq);

    Slice<Gym> findByGymNameContaining(String keyword, Pageable pageable);

    Slice<Gym> findBySportsContaining(String keyword, Pageable pageable);
}
