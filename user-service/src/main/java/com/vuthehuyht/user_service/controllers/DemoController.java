package com.vuthehuyht.user_service.controllers;

import com.vuthehuyht.user_service.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class DemoController {
    @GetMapping(path = "/demo")
    ApiResponse<String> demo() {
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message("demo API is running")
                .build();
    }
}
