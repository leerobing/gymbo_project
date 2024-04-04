package com.example.gymbo_back_end.core.commom.response.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SliceResponseDto<T> {

    List<T> data;

    SliceInfo sliceInfo;
}
