package com.codingshuttle.linkedin.user_service.controllers;

import com.codingshuttle.linkedin.user_service.dto.LoginRequestDto;
import com.codingshuttle.linkedin.user_service.dto.SignupRequestDto;
import com.codingshuttle.linkedin.user_service.dto.UserDto;
import com.codingshuttle.linkedin.user_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        UserDto userDto = authService.signup(signupRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> signup(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }
}
