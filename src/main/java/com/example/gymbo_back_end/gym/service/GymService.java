package com.example.gymbo_back_end.gym.service;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface GymService {

    Gym save(GymSaveRequestDto gymSaveRequestDto);

    Gym find(String gymName);

    Gym find(Long gymSeq);

    List<Gym> findAll();

    Gym update(GymSaveRequestDto gymSaveRequestDto);

    Gym findByGymNumber(String gymNumber);

    Slice<Gym> searchGymName(String keyword, Pageable pageable);


    Slice<Gym> searchGymSports(String keyword, Pageable pageable);

}
