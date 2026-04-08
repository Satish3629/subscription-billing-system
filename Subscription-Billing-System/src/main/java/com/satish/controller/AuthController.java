package com.satish.controller;

import com.satish.dto.LoginInputDto;
import com.satish.dto.LoginOutputDto;
import com.satish.dto.UserDto;
import com.satish.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name="Auth",description = "Auth module API's")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Login",
            description = "🔒 Accessible by all role")
    public ResponseEntity<LoginOutputDto> login(@RequestBody LoginInputDto loginInputDto){
      LoginOutputDto loginOutputDto=  authService.login(loginInputDto);
      return  ResponseEntity.ok(loginOutputDto);
    }

    @PostMapping("/register")
    @Operation(summary = "Register",
            description = "🔒 Accessible by all role")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){

        UserDto savedUserDto = authService.createUser(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

}
