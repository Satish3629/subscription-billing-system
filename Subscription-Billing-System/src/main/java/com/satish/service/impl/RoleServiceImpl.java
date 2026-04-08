package com.satish.service.impl;

import com.satish.dto.RoleDto;
import com.satish.entity.Role;
import com.satish.exception.ResourceNotFoundException;
import com.satish.mapper.RoleMapper;
import com.satish.repository.RoleRepository;
import com.satish.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role= RoleMapper.mapToRole(roleDto);
        Role savedRole=roleRepository.save(role);
        return RoleMapper.mapToRoleDto(savedRole);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List <Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(RoleMapper::mapToRoleDto)
                .toList();
    }

    @Override
    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Role not found with id " + id));
        return RoleMapper.mapToRoleDto(role);
    }

    @Override
    public RoleDto updateRole(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Role not found"));

        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());

        Role savedRole = roleRepository.save(role);
        return RoleMapper.mapToRoleDto(savedRole);
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()-> new ResourceNotFoundException("Role not found with id " + roleId));
        role.setIsActive(false);
        roleRepository.save(role);
    }
}
