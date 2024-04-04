package com.example.gymbo_back_end.gym.dto;

import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.core.commom.response.dto.SliceInfo;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GymSearchResponseDto {

        private String gymName;

        private Address gymAddress;

        private String gymNumber;

        private String managerNumber;

        private String gymSports;

        private List<Map<String, Object>> imageList;

        private SliceInfo sliceInfo;


}
