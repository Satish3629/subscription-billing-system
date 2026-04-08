package com.satish.service;

import com.satish.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    List <RoleDto> getAllRoles();

    RoleDto getRoleById(Long id);

    RoleDto updateRole (Long id, RoleDto roleDto);

    void deleteRole(Long roleId);
}
