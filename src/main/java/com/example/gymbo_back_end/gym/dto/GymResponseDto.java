package com.example.gymbo_back_end.gym.dto;

import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.core.entity.Gym;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class GymResponseDto {

    private String gymName;

    private Address gymAddress;

    private String gymNumber;

    private String managerNumber;

    private String gymSports;

    private List<Map<String, Object>> imageList;


    @Builder
    public GymResponseDto(String gymName, Address gymAddress, String gymNumber, String managerNumber,String gymSports,List<Map<String, Object>> imageList) {
        this.gymName = gymName;
        this.gymSports = gymSports;
        this.gymAddress = gymAddress;
        this.gymNumber = gymNumber;
        this.managerNumber = managerNumber;
        this.imageList = imageList;
    }


    public static GymResponseDto buildDto (Gym gym) {
       return GymResponseDto.builder()
               .gymAddress(gym.getGymAddress())
               .gymSports(gym.getGymSports())
               .gymNumber(gym.getGymNumber())
               .gymName(gym.getGymName())
               .managerNumber(gym.getManagerNumber())
               .build();
    }
    public static GymResponseDto buildPhotoDto (Gym gym,List<Map<String, Object>> imageList) {
        GymResponseDto gymResponseDto = new GymResponseDto();
        gymResponseDto.setGymName(gym.getGymName());
        gymResponseDto.setGymAddress(gym.getGymAddress());
        gymResponseDto.setGymNumber(gym.getGymNumber());
        gymResponseDto.setGymSports(gym.getGymSports());
        gymResponseDto.setManagerNumber(gym.getManagerNumber());
        gymResponseDto.setImageList(imageList);
        return gymResponseDto;
    }
}
