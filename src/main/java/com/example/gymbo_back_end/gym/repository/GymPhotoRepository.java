package com.example.gymbo_back_end.gym.repository;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GymPhotoRepository extends JpaRepository<GymPhoto, Long> {

    @Query("SELECT gp FROM GymPhoto gp WHERE gp.gym = :gym")
    List<GymPhoto> findGymPhotosByGym(@Param("gym") Gym gym);


}
