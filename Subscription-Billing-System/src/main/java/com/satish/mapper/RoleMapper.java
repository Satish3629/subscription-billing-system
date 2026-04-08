package com.satish.mapper;

import com.satish.dto.RoleDto;
import com.satish.entity.Role;

public class RoleMapper {
    public static Role mapToRole(RoleDto roleDto){
        return new Role(
                roleDto.getId(),
                roleDto.getName(),
                roleDto.getDescription(),
                roleDto.getIsActive()
        );
    }

    public static RoleDto mapToRoleDto(Role role){
        return new RoleDto(
                role.getId(),
                role.getName(),
                role.getDescription(),
                role.getIsActive()
        );
    }

}
