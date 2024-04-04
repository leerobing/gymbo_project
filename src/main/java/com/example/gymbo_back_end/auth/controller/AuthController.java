package com.example.gymbo_back_end.auth.controller;

import com.example.gymbo_back_end.auth.service.AuthService;
import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.jwt.TokenInfo;
import com.example.gymbo_back_end.auth.dto.AuthLoginRequestDto;
import com.example.gymbo_back_end.member.dto.ReissueTokensRequestDto;
import com.example.gymbo_back_end.auth.dto.AuthJoinRequestDto;
import com.example.gymbo_back_end.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join") //회원 가입
    public ResponseEntity<ResBodyModel> join(@RequestBody AuthJoinRequestDto authLoginRequestDto) {
        Member savedMember = authService.save(authLoginRequestDto);
        log.info("savedMember = {}",savedMember);
        return AetResponse.toResponse(SuccessCode.SUCCESS);
    }

    @PostMapping("/login") //로그인
    public TokenInfo login(@RequestBody AuthLoginRequestDto authLoginRequestDto) {
        String memberId = authLoginRequestDto.getMemberId();
        String password = authLoginRequestDto.getPassword();
        TokenInfo tokenInfo = authService.login(memberId, password).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
        return tokenInfo;
    }

    @PostMapping("/re_token")//토큰 재발급 요청
    private TokenInfo reToken(@RequestHeader("refreshToken") String refreshToken,
                                        @RequestBody ReissueTokensRequestDto reissueTokensRequestDto) {
        TokenInfo tokenInfo = authService.reissueTokens(refreshToken, reissueTokensRequestDto);

        return tokenInfo;
    }

    @GetMapping("/member_info")
    @ResponseBody
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails){
        if(userDetails!=null){
            System.out.println("로그인 된 상태입니다.");
            return userDetails.getUsername();
        }
        return "확인 불가";
    }

}
