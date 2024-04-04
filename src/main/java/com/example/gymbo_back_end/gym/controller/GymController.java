package com.example.gymbo_back_end.gym.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.dto.SliceInfo;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;
import com.example.gymbo_back_end.gym.dto.GymPhotoRequestDto;
import com.example.gymbo_back_end.gym.dto.GymResponseDto;
import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;
import com.example.gymbo_back_end.gym.dto.GymSearchResponseDto;
import com.example.gymbo_back_end.gym.mapper.GymMapper;
import com.example.gymbo_back_end.gym.service.GymPhotoService;
import com.example.gymbo_back_end.gym.service.GymService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/v1/gyms")
@RequiredArgsConstructor
public class GymController {


    private final GymService gymService;
    private final GymPhotoService gymPhotoService;
    private final GymMapper gymMapper;

    /**
     * 운동시설 등록 localhost8080/v1/gyms/save
     * */
    @PostMapping("/save")
    public ResponseEntity<ResBodyModel> gymRegistration(@RequestBody GymSaveRequestDto gymSaveRequestDto) {
        Gym gym = gymService.save(gymSaveRequestDto);
        GymResponseDto gymResponseDto = GymResponseDto.buildDto(gym);
        return AetResponse.toResponse(SuccessCode.SUCCESS, gymResponseDto);

    }

    @PostMapping(value = "/file_save",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE}) //운동시설 사진 등록
    public ResponseEntity<ResBodyModel> gymPhotoSave(@RequestPart(required = false)  List<MultipartFile> files
            ,@RequestPart(required = false) GymPhotoRequestDto gymPhotoRequestDto ) throws Exception {
        List<GymPhoto> gymPhotos = gymPhotoService.saveGymPhoto(gymPhotoRequestDto, files);

        return AetResponse.toResponse(SuccessCode.SUCCESS,gymPhotos);
    }

    /**
     * 운동시설 사진 수정
     * */
    @PostMapping(value = "/file_update",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE}) // 운동 시설 사진 업데이트
    public ResponseEntity<ResBodyModel> gymPhotoUpdate(@RequestPart(required = false) List<MultipartFile> files
            ,@RequestPart(required = false) GymPhotoRequestDto gymPhotoRequestDto) throws Exception {

        List<GymPhoto> gymPhoto = gymPhotoService.findGymPhoto(gymPhotoRequestDto.getGymNumber());
        for (GymPhoto photo : gymPhoto) {
            gymPhotoService.gymPhotoDelete(photo);
        }

        List<GymPhoto> gymPhotos = gymPhotoService.saveGymPhoto(gymPhotoRequestDto, files);

        return AetResponse.toResponse(SuccessCode.SUCCESS,gymPhotos);
    }

    /**
     * 운동시설 사진 seq로 조회
     * */
    @GetMapping(value = "/file/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<ResBodyModel> fileFind(@PathVariable Long id) throws IOException {

        GymPhoto gymPhoto = gymPhotoService.findByGymPhotoSeq(id);

         /**
        new File(""): 기본적으로 현재 작업 디렉터리를 참조하는 빈 문자열로 새 File 객체를 생성합니다.
        .getAbsolutePath(): 현재 작업 디렉터리의 절대 경로인 File 개체의 절대 경로 이름을 검색합니다.
        + File.separator + File.separator: 획득한 절대 경로를 파일 구분 기호와 연결합니다.
        File.separator를 두 번 사용하는 이유는 경로와 후속 파일 또는 디렉터리 이름 사이에 올바른 파일 구분 기호가 추가되었는지 확인하기 위한 것입니다
        */
        String absolutePath
                = new File("").getAbsolutePath() + File.separator + File.separator;

        String path = gymPhoto.getFilePath();

        InputStream imageStream = new FileInputStream(absolutePath + path);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return AetResponse.toResponse(SuccessCode.SUCCESS,imageByteArray);
    }

    /**
     * 운동시설 seq로 사진 조회
     * */
    @GetMapping(value = "/files/{gym_seq}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResBodyModel> getAllImages(@PathVariable Long gym_seq) throws IOException {
        Gym gym = gymService.find(gym_seq);
        List<GymPhoto> gymPhotos = gymPhotoService.findGymPhoto(gym.getGymNumber());
        List<Map<String, Object>> imageList = gymMapper.gymPhotoMapping(gymPhotos);

        return AetResponse.toResponse(SuccessCode.SUCCESS, imageList);
    }

    /**
     * 운동시설 번호로 사진 조회
     * */
    @PostMapping(value = "/files", produces = {MediaType.APPLICATION_JSON_VALUE}) //
    public ResponseEntity<ResBodyModel> getAllImages(@RequestBody GymPhotoRequestDto gymPhotoRequestDto) throws IOException {
        List<GymPhoto> gymPhotos = gymPhotoService.findGymPhoto(gymPhotoRequestDto.getGymNumber());
        List<Map<String, Object>> imageList = gymMapper.gymPhotoMapping(gymPhotos);

        return AetResponse.toResponse(SuccessCode.SUCCESS, imageList);
    }

    /**
     * 운동시설명으로 조회 localhost8080/v1/gyms/서울유나이티드
     * */
    @GetMapping("/{gymName}")
    public ResponseEntity<ResBodyModel> findByGymName ( @PathVariable String gymName) throws IOException {

        Gym gym = gymService.find(gymName);
        List<GymPhoto> gymPhotos = gymPhotoService.findGymPhoto(gym.getGymNumber());
        List<Map<String, Object>> imageList = gymMapper.gymPhotoMapping(gymPhotos);
        GymResponseDto gymResponseDto = GymResponseDto.buildPhotoDto(gym,imageList);

        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDto);

    }

    /**
     * 운동시설 전체 조회
     * */
    @GetMapping("/all")
    public ResponseEntity<ResBodyModel> findAllGym () throws IOException {
        List<Gym> gyms = gymService.findAll();
        List<GymResponseDto> gymResponseDtoList = gymMapper.toResponse(gyms);
        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDtoList);
    }

    /**
     * 운동시설 정보 업데이트
     * */
    @PatchMapping //운동시설 업데이트
    public ResponseEntity<ResBodyModel> updateGym(@RequestBody GymSaveRequestDto gymSaveRequestDto) {
        Gym gym = gymService.update(gymSaveRequestDto);
        GymResponseDto gymResponseDto = GymResponseDto.buildDto(gym);
        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDto);

    }

    /**
     * 운동시설명으로 검색하는 컨트롤러
     * */
    @GetMapping("/search/gym_name")
    public ResponseEntity<ResBodyModel> searchGymName(@RequestParam("keyword") String keyword, Pageable pageable) {
        Slice<Gym> gyms = gymService.searchGymName(keyword, pageable);
        SliceInfo sliceInfo = new SliceInfo(pageable, gyms.getNumberOfElements(), gyms.hasNext());
        List<GymSearchResponseDto> gymSearchResponseDtos = gymMapper.toResponse(gyms,sliceInfo);
        return AetResponse.toResponse(SuccessCode.SUCCESS,gymSearchResponseDtos);
    }

    /**
     * 운동시설명으로 검색하는 컨트롤러
     * */
    @GetMapping("/search/gym_sports")
    public ResponseEntity<ResBodyModel> searchGymSports(@RequestParam("keyword") String keyword, Pageable pageable) {
        Slice<Gym> gyms = gymService.searchGymSports(keyword, pageable);
        SliceInfo sliceInfo = new SliceInfo(pageable, gyms.getNumberOfElements(), gyms.hasNext());
        List<GymSearchResponseDto> gymSearchResponseDtos = gymMapper.toResponse(gyms,sliceInfo);
        return AetResponse.toResponse(SuccessCode.SUCCESS,gymSearchResponseDtos);
    }

}