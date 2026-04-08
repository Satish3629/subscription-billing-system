package com.satish.service.impl;

import com.satish.dto.UserDto;
import com.satish.entity.User;
import com.satish.exception.ResourceNotFoundException;
import com.satish.mapper.UserMapper;
import com.satish.repository.UserRepository;
import com.satish.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getAllUsers(int page, int size, boolean isActive) {
        Pageable pageable= PageRequest.of(page,size);
        Page<User> users = userRepository.findByIsActive(isActive, pageable);
        return users;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id " + userId));
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id " + id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getByUserId(Long id) {
       User user=userRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("User not found with id " + id));
       return UserMapper.mapToUserDto(user);
    }

}
