package com.example.gymbo_back_end.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@Data
public class MemberPhotoTestDto {

    private String memberId;

    private List<MultipartFile> files;
}
