package com.example.gymbo_back_end.gym.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PhotoDto {

    private String origFileName;
    private String filePath;
    private Long fileSize;

    public PhotoDto() {
    }

    @Builder
    public PhotoDto(String origFileName, String filePath, Long fileSize) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
