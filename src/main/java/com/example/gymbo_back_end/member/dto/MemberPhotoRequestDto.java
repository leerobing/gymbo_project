package com.example.gymbo_back_end.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Data
public class MemberPhotoRequestDto {

    private String memberId;
}
