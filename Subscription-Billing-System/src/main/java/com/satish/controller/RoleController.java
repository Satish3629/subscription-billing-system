package com.satish.controller;

import com.satish.dto.RoleDto;
import com.satish.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@Tag(name="Role", description = "Role module API's")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create Role (ADMIN only)",
            description = "🔒 Accessible by admin role")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto){
       RoleDto savedRoleDto= roleService.createRole(roleDto);
       return new ResponseEntity<>(savedRoleDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get All Roles",
            description = "🔒 Accessible by all role")
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> roleDto = roleService.getAllRoles();
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Role By Id",
            description = "🔒 Accessible by all role")
    public  ResponseEntity<RoleDto> getRoleById (@PathVariable Long id){
        RoleDto roleDto = roleService.getRoleById(id);
        return ResponseEntity.ok(roleDto);
    }

    @PutMapping ("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update Role (ADMIN only)",
            description = "🔒 Accessible by admin role")
    public ResponseEntity<RoleDto> updateRole(@PathVariable("id") Long roleId,
                                              @RequestBody RoleDto roleDto) {
        RoleDto savedRoleDto = roleService.updateRole(roleId,roleDto);
        return ResponseEntity.ok(savedRoleDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Role (ADMIN only)",
            description = "🔒 Accessible by admin role")
    public ResponseEntity<String> deleteRole (@PathVariable("id") Long roleId){
        roleService.deleteRole(roleId);
        return ResponseEntity.ok("Role Deleted successfully");
    }

}
