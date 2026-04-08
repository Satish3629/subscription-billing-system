package com.satish.service.impl;

import com.satish.dto.LoginInputDto;
import com.satish.dto.LoginOutputDto;
import com.satish.dto.UserDto;
import com.satish.entity.Role;
import com.satish.entity.User;
import com.satish.exception.BadRequestException;
import com.satish.exception.ResourceNotFoundException;
import com.satish.mapper.UserMapper;
import com.satish.repository.RoleRepository;
import com.satish.repository.UserRepository;
import com.satish.service.AuthService;
import com.satish.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RoleRepository roleRepository;


    @Override
    public LoginOutputDto login(LoginInputDto loginInputDto) {

        User user=userRepository.findByEmail(loginInputDto.getEmail())
                .orElseThrow(()->new ResourceNotFoundException("User not found..!!"));

        LoginOutputDto loginOutputDto=new LoginOutputDto();

        if(passwordEncoder.matches(loginInputDto.getPassword(),user.getPassword())){
            String token=jwtUtil.generateToken(user);
            loginOutputDto.setMessage("Login Successful..!!");
            loginOutputDto.setToken(token);
        }
        else
            throw new BadRequestException("Password not matched..!!");

        return loginOutputDto;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        LocalDate date = LocalDate.now();

        User user= UserMapper.mapToUsers(userDto);
        Role role=roleRepository.findById(userDto.getRoleId())
                        .orElseThrow(()->new ResourceNotFoundException("Role not found..!!"));

        user.setRole(role);
        user.setIsActive(true);
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        String encodedPassword =passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

}
