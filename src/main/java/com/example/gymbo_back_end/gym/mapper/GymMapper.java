package com.example.gymbo_back_end.gym.mapper;

import com.example.gymbo_back_end.core.commom.response.dto.SliceInfo;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;
import com.example.gymbo_back_end.gym.dto.GymResponseDto;
import com.example.gymbo_back_end.gym.dto.GymSearchResponseDto;
import org.springframework.data.domain.Slice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GymMapper {

    List<Map<String, Object>> gymPhotoMapping(List<GymPhoto> gymPhotos) throws IOException;
    List<GymResponseDto> toResponse(List<Gym> gyms) throws IOException;
    List<GymSearchResponseDto> toResponse(Slice<Gym> gyms, SliceInfo sliceInfo);
}
