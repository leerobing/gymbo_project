package com.example.gymbo_back_end.gym.service;

import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.core.entity.Gym;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.Payment;
import com.example.gymbo_back_end.gym.dao.GymDao;
import com.example.gymbo_back_end.gym.dao.GymPhotoDao;

import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;
import com.example.gymbo_back_end.gym.handler.GymFileHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GymServiceImpl implements GymService {

    private final GymDao gymDao;

    /**
     * 운동시설 저장 로직
     * */
    @Override
    public Gym save(GymSaveRequestDto gymSaveRequestDto) {

        Address address = new Address(gymSaveRequestDto.getCity()
                ,gymSaveRequestDto.getStreet()
                ,gymSaveRequestDto.getZipCode());

        Gym gym = Gym.builder()
                .gymName(gymSaveRequestDto.getGymName())
                .gymAddress(address)
                .gymNumber(gymSaveRequestDto.getGymNumber())
                .gymSports(gymSaveRequestDto.getGymSports())
                .managerNumber(gymSaveRequestDto.getManagerNumber())
                .build();

        return gymDao.save(gym);
    }

    /**
     * 운동시설명으로 단건 조회
     * */
    @Override
    public Gym find(String gymName) {
        Gym findGym = gymDao.findByGymName(gymName);
        return findGym;
    }

    /**
     * 운동시설 seq 로 단건 조회
     * */
    @Override
    public Gym find(Long gymSeq) {
       return gymDao.find(gymSeq);
    }

    /**
     * 운동시설 전체 조회
     * */
    @Override
    public List<Gym> findAll() {
        List<Gym> gyms = gymDao.findAll();
        return gyms;
    }

    /**
     * 운동시설 정보 수정
     * */
    @Override
    public Gym update(GymSaveRequestDto gymSaveRequestDto) {
        Gym gym = gymDao.findByGymNumber(gymSaveRequestDto.getGymNumber());
        gym.changeInfo(gymSaveRequestDto);
        return gym;
    }

    /**
     * 운동시설 사업자 번호로 단건 조회
     * */
    @Override
    public Gym findByGymNumber(String gymNumber) {
        return gymDao.findByGymNumber(gymNumber);
    }


    /**
     * 운동시설명으로 검색하는 서비스 로직
     * */
    @Override
    public Slice<Gym> searchGymName(String keyword, Pageable pageable) {
        return gymDao.findByGymNameContaining(keyword,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                        Sort.Direction.DESC,"gymSeq")
        );
    }

    /**
     * 운동시설명으로 검색하는 서비스 로직
     * */
    @Override
    public Slice<Gym> searchGymSports(String keyword, Pageable pageable) {
        return gymDao.findBySportsContaining(keyword,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                        Sort.Direction.DESC,"gymSeq")
        );
    }

}

