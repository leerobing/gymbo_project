package com.example.gymbo_back_end.auth.controller;


import com.example.gymbo_back_end.auth.dto.AuthEmailRequestDto;
import com.example.gymbo_back_end.auth.service.AuthEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/email")
public class AuthEmailController {

    private final AuthEmailService authEmailService;

    @GetMapping("/{email_addr}/authcode")
    public ResponseEntity<String> sendEmailPath(@PathVariable String email_addr) throws MessagingException {
        authEmailService.sendEmail(email_addr);
        return ResponseEntity.ok("이메일을 확인하세요");
    }

    @PostMapping("/{email_addr}/authcode")
    public ResponseEntity<String> sendEmailAndCode(@PathVariable String email_addr, @RequestBody AuthEmailRequestDto dto) throws NoSuchAlgorithmException {
        if (authEmailService.verifyEmailCode(email_addr, dto.getCode())) {
            return ResponseEntity.ok("회원가입 가능합니다.");
        }
        return ResponseEntity.ok("잘못됨");
    }
}