package com.satish.service;

import com.satish.dto.LoginInputDto;
import com.satish.dto.LoginOutputDto;
import com.satish.dto.UserDto;

public interface AuthService {

    LoginOutputDto login(LoginInputDto loginInputDto);

    UserDto createUser(UserDto userDto);

}
