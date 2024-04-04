package com.example.gymbo_back_end.gym.service;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;
import com.example.gymbo_back_end.gym.dao.GymDao;
import com.example.gymbo_back_end.gym.dao.GymPhotoDao;
import com.example.gymbo_back_end.gym.dto.GymPhotoRequestDto;
import com.example.gymbo_back_end.gym.handler.GymFileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GymPhotoServiceImpl implements GymPhotoService{

    private final GymDao gymDao;
    private final GymFileHandler gymFileHandler;
    private final GymPhotoDao gymPhotoDao;

    /**
     * 운동시설 사진 저장
     * */
    @Override
    @Transactional
    public List<GymPhoto> saveGymPhoto(GymPhotoRequestDto gymPhotoRequestDto, List<MultipartFile> files) throws Exception {

        Gym gym = gymDao.findByGymNumber(gymPhotoRequestDto.getGymNumber());
        List<GymPhoto> photoList = gymFileHandler.parseFileInfo(files);

        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()) {
            for(GymPhoto photo : photoList) {
                // 파일을 DB에 저장
                GymPhoto gymPhoto = gymPhotoDao.save(photo);
                gym.addPhoto(gymPhoto);
            }
        }

        return photoList;
    }
    /**
     * 운동 시설 사진 사업자 번호로 조회
     * */
    @Override
    public List<GymPhoto> findGymPhoto(String gymNumber) {
        Gym gym = gymDao.findByGymNumber(gymNumber);
        List<GymPhoto> gymPhotosByGym = gymPhotoDao.findGymPhotosByGym(gym);
        return gymPhotosByGym;
    }


    /**
     * 운동시설 사진 seq 로 조회
     * */
    @Override
    public GymPhoto findByGymPhotoSeq(Long gymPhotoSeq) {
        return gymPhotoDao.findById(gymPhotoSeq);
    }

    /**
     * 운동시설 사진 삭제
     * */
    @Override
    public void gymPhotoDelete(GymPhoto gymPhoto) {
        gymPhotoDao.delete(gymPhoto);
    }
}
