package com.example.gymbo_back_end.auth.service;

import com.example.gymbo_back_end.auth.dto.AuthJoinRequestDto;
import com.example.gymbo_back_end.core.commom.exception.auth.IllegalAccessTokenException;
import com.example.gymbo_back_end.core.commom.exception.auth.InvalidPasswordException;
import com.example.gymbo_back_end.core.commom.exception.member.MemberIdAlreadyExistsException;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.jwt.JwtTokenProvider;
import com.example.gymbo_back_end.jwt.TokenInfo;
import com.example.gymbo_back_end.member.controller.MemberRoles;
import com.example.gymbo_back_end.member.dao.MemberDao;
import com.example.gymbo_back_end.member.dto.ReissueTokensRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final BCryptPasswordEncoder encoder;
    private final MemberDao memberDao;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override //회원가입
    public Member save(AuthJoinRequestDto authJoinRequestDto) {

        String encode = encoder.encode(authJoinRequestDto.getPassword());

        Member member = Member.builder()
                .memberId(authJoinRequestDto.getMemberId())
                .password(encode)
                .nickName(authJoinRequestDto.getNickName())
                .roles(Collections.singletonList(MemberRoles.USER.getRole()))
                .build();

        existsByMemberId(member.getMemberId());

        return memberDao.save(member);
    }


    @Transactional //로그인
    public Optional<TokenInfo> login(String memberId, String password) {

        Member member = memberDao.findByMemberId(memberId);


        if (encoder.matches(password,member.getPassword())==true) { //비밀번호가 암호화된 비민번호와 일치한지 확인

            // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
            // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, member.getPassword());

            // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
            // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

            //4. 리프레쉬 토큰 저장
            member.setRefreshToken(tokenInfo.getRefreshToken());

            return Optional.ofNullable(tokenInfo);
        } else {
            throw  new InvalidPasswordException("비밀번호 인증에 실패했습니다.");
        }
    }

    @Override
    @Transactional // 토큰 재발급 로직
    public TokenInfo reissueTokens(String refreshToken, ReissueTokensRequestDto reissueTokensRequestDto) {
        Member member = memberDao.findByMemberId(reissueTokensRequestDto.getMemberId());
        if (jwtTokenProvider.validateToken(refreshToken) &&
                refreshToken.equals(member.getRefreshToken())) {
            TokenInfo tokenInfo = reissueTokensFromUser(member);
            member.setRefreshToken(tokenInfo.getRefreshToken());
            return tokenInfo;
        }

        throw new IllegalAccessTokenException("토큰 요청 정보가 올바르지 않습니다.", 402);
    }

    /**
     * 아이디 중복 검사
     * */
    public void existsByMemberId(String memberId) {

        Boolean result = memberDao.existsByMemberId(memberId);
        if (result) {
            throw new MemberIdAlreadyExistsException("아이디가 존재합니다.");
        }

    }


    private TokenInfo reissueTokensFromUser(Member member) {

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getMemberId(), member.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);
    }

}
