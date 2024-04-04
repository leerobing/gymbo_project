package com.example.gymbo_back_end.core.commom.response;


import com.example.gymbo_back_end.core.commom.code.BodyCode;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AetResponse {

    private static ResBodyModel toBody(BodyCode bodyCode) {
        return ResBodyModel.builder()
                .code(bodyCode.getCode())
                .description(bodyCode.getMessage())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")))
                .data(null)
                .build();
    }

    private static ResBodyModel toBody(BodyCode bodyCode,Object data) {
        return ResBodyModel.builder()
                .code(bodyCode.getCode())
                .description(bodyCode.getMessage())
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")))
                .data(data)
                .build();
    }

    public static ResponseEntity<ResBodyModel> toResponse(BodyCode bodyCode) {
        return ResponseEntity.ok().body(toBody(bodyCode));
    }

    public static ResponseEntity<ResBodyModel> toResponse(BodyCode bodyCode, int status) {
        return ResponseEntity.status(status).body(toBody(bodyCode));
    }

    public static ResponseEntity<ResBodyModel> toResponse(BodyCode bodyCode, Object body) {
        return ResponseEntity.ok().body(toBody(bodyCode, body));
    }
    public static ResponseEntity<ResBodyModel> toResponse(BodyCode bodyCode, Object body, int status) {
        return ResponseEntity.status(status).body(toBody(bodyCode, body));
    }

    public static ResponseEntity<ResBodyModel> toResponse(BodyCode bodyCode, Object body, int status, HttpHeaders headers) {
        return ResponseEntity.status(status).headers(headers).body(toBody(bodyCode, body));
    }
}