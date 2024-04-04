package com.example.gymbo_back_end.gym.mapper;

import com.example.gymbo_back_end.core.commom.response.dto.SliceInfo;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;
import com.example.gymbo_back_end.gym.dto.GymResponseDto;
import com.example.gymbo_back_end.gym.dto.GymSearchResponseDto;
import com.example.gymbo_back_end.gym.service.GymPhotoService;
import com.example.gymbo_back_end.gym.service.GymService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GymMapperImpl implements GymMapper {

    private final GymService gymService;
    private final GymPhotoService gymPhotoService;

    /**
     * 이미지 인코딩 작업
     *
     new File(""): 기본적으로 현재 작업 디렉터리를 참조하는 빈 문자열로 새 File 객체를 생성합니다.
     .getAbsolutePath(): 현재 작업 디렉터리의 절대 경로인 File 개체의 절대 경로 이름을 검색합니다.
     + File.separator + File.separator: 획득한 절대 경로를 파일 구분 기호와 연결합니다.
     File.separator를 두 번 사용하는 이유는 경로와 후속 파일 또는 디렉터리 이름 사이에 올바른 파일 구분 기호가 추가되었는지 확인하기 위한 것입니다.
     */    @Override
    public List<Map<String, Object>> gymPhotoMapping(List<GymPhoto> gymPhotos) throws IOException {

        List<Map<String, Object>> imageList = new ArrayList<>();

        for (GymPhoto gymPhoto : gymPhotos) {

            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            String path = gymPhoto.getFilePath();
            InputStream imageStream = new FileInputStream(absolutePath + path);
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();

            // 이미지 바이트 배열을 Base64로 인코딩하여 문자열로 변환
            String base64EncodedImage = Base64.encodeBase64String(imageByteArray);

            Map<String, Object> imageInfo = new HashMap<>();
            imageInfo.put("fileName", gymPhoto.getOrigFileName());
            imageInfo.put("imageBytes", base64EncodedImage);
            imageList.add(imageInfo);
        }
        return imageList;
    }

    /**
     * 운동시설 조회 시 내보낼 응답 객체 생성
     * */
    @Override
    public List<GymResponseDto> toResponse(List<Gym> gyms) throws IOException {
        List<GymResponseDto> gymResponseDtoList = new ArrayList<>();
        return gyms.stream().map(gym -> {

            List<GymPhoto> gymPhotos = gymPhotoService.findGymPhoto(gym.getGymNumber());
            List<Map<String, Object>> imageList = null;
            try {
                imageList = gymPhotoMapping(gymPhotos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return GymResponseDto.builder()
                    .gymName(gym.getGymName())
                    .managerNumber(gym.getManagerNumber())
                    .gymSports(gym.getGymSports())
                    .gymAddress(gym.getGymAddress())
                    .imageList(imageList)
                    .gymNumber(gym.getGymNumber())
                    .build();
        }).collect(Collectors.toList());

//        List<GymResponseDto> gymResponseDtoList = new ArrayList<>();
//        for (Gym gym : gyms) {
//            List<GymPhoto> gymPhotos = gymPhotoService.findGymPhoto(gym.getGymNumber());
//            List<Map<String, Object>> imageList = gymPhotoMapping(gymPhotos);
//            GymResponseDto gymResponseDto = GymResponseDto.buildPhotoDto(gym,imageList);
//            gymResponseDtoList.add(gymResponseDto);
//        }
//        return gymResponseDtoList;
    }

    /**
     * 키워드로 운동시설 검색 시 내보낼 응답 객체 생성
     * */
    public List<GymSearchResponseDto> toResponse(Slice<Gym> gyms, SliceInfo sliceInfo ) {
        return gyms.stream().map(
                gym -> {
                    List<GymPhoto> gymPhoto = gymPhotoService.findGymPhoto(gym.getGymNumber());
                    List<Map<String, Object>> photoMapping = null;

                    try {
                        photoMapping = gymPhotoMapping(gymPhoto);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return GymSearchResponseDto.builder()
                            .gymNumber(gym.getGymNumber())
                            .gymAddress(gym.getGymAddress())
                            .gymSports(gym.getGymSports())
                            .gymName(gym.getGymName())
                            .imageList(photoMapping)
                            .managerNumber(gym.getManagerNumber())
                            .sliceInfo(sliceInfo)
                            .build();
                }).collect(Collectors.toList());
     }
}
