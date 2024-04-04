package com.example.gymbo_back_end.gym.dao;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;
import com.example.gymbo_back_end.gym.repository.GymPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaGymPhotoDao implements GymPhotoDao{

    private final GymPhotoRepository gymPhotoRepository;

    @Override
    public List<GymPhoto> findGymPhotosByGym(Gym gym) {
       return gymPhotoRepository.findGymPhotosByGym(gym);
    }

    @Override
    public GymPhoto findById(Long gymPhotoSeq) {
        return gymPhotoRepository.findById(gymPhotoSeq).orElseThrow(() -> new EntityNotFoundException("파일이 존재하지 않습니다."));
    }

    @Override
    public GymPhoto save(GymPhoto gymPhoto) {
        return gymPhotoRepository.save(gymPhoto);
    }

    @Override
    public void delete(GymPhoto gymPhoto) {
        gymPhotoRepository.delete(gymPhoto);
    }
}
