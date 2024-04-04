package com.example.gymbo_back_end.member.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPhoto;
import com.example.gymbo_back_end.core.entity.MemberPoint;
import com.example.gymbo_back_end.member.dto.MemberPhotoRequestDto;
import com.example.gymbo_back_end.member.dto.MemberPhotoTestDto;
import com.example.gymbo_back_end.member.dto.MemberRequestDto;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;
import com.example.gymbo_back_end.member.mapper.MemberMapper;
import com.example.gymbo_back_end.member.mapper.MemberPhotoMapper;
import com.example.gymbo_back_end.member.service.MemberPointService;
import com.example.gymbo_back_end.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberPointService memberPointService;
    private final MemberMapper memberMapper;
    private final MemberPhotoMapper memberPhotoMapper;


    /**
     * 로그인시 단일 회원 조회
     * */
    @GetMapping("login_view/{memberId}")
    public ResponseEntity<ResBodyModel> loginViewRead(@PathVariable String memberId) throws IOException {
        Member member = memberService.find(memberId);
        Optional<MemberPoint> optionalMemberPointFind = memberPointService.optionalMemberPointFind(memberId);
        List<MemberPhoto> memberPhoto = memberService.findMemberPhoto(memberId);
        List<Map<String, Object>> memberPhotoMapping = memberPhotoMapper.toResponse(memberPhoto);
        ResponseMemberInfoDto responseMemberInfoDto = memberMapper.toResponse(optionalMemberPointFind, member,memberPhotoMapping);
        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDto);
    }

    @GetMapping("/{memberId}") //단일 회원 조회
    public ResponseEntity<ResBodyModel> read(@PathVariable String memberId) {
        Member member = memberService.find(memberId);
        ResponseMemberInfoDto responseMemberInfoDto = ResponseMemberInfoDto.builder()
                .memberId(member.getMemberId())
                .nickName(member.getNickName())
                .build();
        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDto);
    }

    @GetMapping()//전체 회원 조회
    public ResponseEntity<ResBodyModel> readAll() {
        List<Member> members = memberService.findAll();
        List<ResponseMemberInfoDto> responseMemberInfoDtos = memberMapper.toResponse(members);
        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDtos);

    }

    @PatchMapping() //회원 정보 수정
    public ResponseEntity<ResBodyModel> update(@RequestBody MemberRequestDto requestMemberJoinDto) {
        Member member = memberService.update(requestMemberJoinDto);
        Optional<MemberPoint> optionalMemberPointFind = memberPointService.optionalMemberPointFind(member.getMemberId());
        ResponseMemberInfoDto responseMemberInfoDto = memberMapper.toResponse(optionalMemberPointFind, member);
        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDto);
    }

    @DeleteMapping("/{memberId}") //회원 삭제
    public ResponseEntity<ResBodyModel> delete(@PathVariable String memberId) {
        memberService.delete(memberId);
        return AetResponse.toResponse(SuccessCode.SUCCESS);
    }

    @DeleteMapping("delete/{memberSeq}") //회원 삭제
    public ResponseEntity<ResBodyModel> delete(@PathVariable Long memberSeq) {
        memberService.delete(memberSeq);
        return AetResponse.toResponse(SuccessCode.SUCCESS);
    }
    /**
     * 회원 프로필 저장
     * */
    @PostMapping(value = "/file_save",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResBodyModel> memberPhotoSave(@RequestPart(required = false)  List<MultipartFile> files
            ,@RequestPart(required = false) MemberPhotoRequestDto memberPhotoRequestDto) throws Exception {
        log.info("MemberPhotoRequest: {}",memberPhotoRequestDto.getMemberId());
        List<MemberPhoto> memberPhotos = memberService.saveMemberPhoto(memberPhotoRequestDto,files);
        return AetResponse.toResponse(SuccessCode.SUCCESS,memberPhotos);
    }

//    @PostMapping("/file_save")
//    public ResponseEntity<ResBodyModel> memberPhotoSave(@ModelAttribute MemberPhotoTestDto memberPhotoRequestDto) throws Exception {
//        log.info("MemberPhotoRequest_memberId: {}",memberPhotoRequestDto.getMemberId());
//        log.info("MemberPhotoRequest_files: {}",memberPhotoRequestDto.getFiles());
//        List<MultipartFile> files = memberPhotoRequestDto.getFiles();
//        MemberPhotoRequestDto memberPhotoRequestDto1 = new MemberPhotoRequestDto();
//        memberPhotoRequestDto1.setMemberId(memberPhotoRequestDto.getMemberId());
//        List<MemberPhoto> memberPhotos = memberService.saveMemberPhoto(memberPhotoRequestDto1,files);
//        return AetResponse.toResponse(SuccessCode.SUCCESS,memberPhotos);
//    }

    /**
     * 회원 프로필 update
     * */
    @PostMapping(value = "/file_update",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})// 회원 프로필 수정
    public ResponseEntity<ResBodyModel> memberPhotoUpdate(@RequestPart(required = false)  List<MultipartFile> files
            ,@RequestPart(required = false) MemberPhotoRequestDto memberPhotoRequestDto) throws Exception {
        log.info("MemberPhotoRequest: {}",memberPhotoRequestDto.getMemberId());
        Member member = memberService.find(memberPhotoRequestDto.getMemberId());
        List<MemberPhoto> memberPhoto = memberService.findMemberPhoto(member.getMemberSeq());

        for (MemberPhoto photo : memberPhoto) {
           memberService.memberPhotoDelete(photo);
        }

        List<MemberPhoto> memberPhotos = memberService.saveMemberPhoto(memberPhotoRequestDto,files);
        return AetResponse.toResponse(SuccessCode.SUCCESS,memberPhotos);
    }

    /**
     * 회원 프로필 seq로 조회
     * */
//    @GetMapping(value = "/file_find/{memberSeq}",produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<ResBodyModel> memberPhotoFind(@PathVariable("memberSeq") Long memberSeq ) throws Exception {
//        List<MemberPhoto> memberPhotos = memberService.findMemberPhoto(memberSeq);
//        List<Map<String, Object>> response = memberPhotoMapper.toResponse(memberPhotos);
//        return AetResponse.toResponse(SuccessCode.SUCCESS,response);
//    }

    /**
     * 회원 프로필 회원 아이디로 조회
     * */
    @GetMapping(value = "/file_find/{memberId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResBodyModel> memberPhotoFindByMemberId(@PathVariable("memberId") String memberId ) throws Exception {
        List<MemberPhoto> memberPhotos = memberService.findMemberPhoto(memberId);
        List<Map<String, Object>> response = memberPhotoMapper.toResponse(memberPhotos);
        return AetResponse.toResponse(SuccessCode.SUCCESS,response);
    }
}
