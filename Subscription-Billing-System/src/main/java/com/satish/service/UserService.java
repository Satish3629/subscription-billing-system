package com.satish.service;

import com.satish.dto.UserDto;
import com.satish.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> getAllUsers(int page, int size, boolean isActive);

    void deleteUser(Long userId);

    UserDto updateUser(Long id, UserDto userDto);

    UserDto getByUserId(Long id);

}
