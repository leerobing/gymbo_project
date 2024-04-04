package com.example.gymbo_back_end.gym.dao;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;

import java.util.List;

public interface GymPhotoDao {

    List<GymPhoto> findGymPhotosByGym(Gym gym);

    GymPhoto findById(Long gymPhotoSeq);

    GymPhoto save(GymPhoto gymPhoto);

    void delete(GymPhoto gymPhoto);
}
