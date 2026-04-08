package com.satish.mapper;

import com.satish.dto.UserDto;
import com.satish.entity.User;

public class UserMapper {

    public  static User mapToUsers(UserDto userDto){

        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getPhone(),
                userDto.getRoleId(),
                userDto.getIsActive(),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt()
        );
    }

    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getRole().getId(),
                user.getIsActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

}
