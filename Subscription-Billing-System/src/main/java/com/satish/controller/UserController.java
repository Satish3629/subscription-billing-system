package com.satish.controller;

import com.satish.dto.UserDto;
import com.satish.entity.User;
import com.satish.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name="User", description = "User module API's")
public class UserController {

    private final UserService userService;

@GetMapping
@Operation(summary = "Get All Users (ADMIN only)",
            description = "🔒 Accessible only by admin role")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Page<User>> getAllUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required=false) boolean isActive
){
        Page<User> userDtos =userService.getAllUsers(page,size,isActive);
        return ResponseEntity.ok(userDtos);
}

@DeleteMapping ("/{id}")
@PreAuthorize("hasRole('ADMIN')")
@Operation(summary = "Delete User (ADMIN only)",
            description = "🔒 Accessible by admin role")
public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted sucessfully");
}

@PutMapping ("/{id}")
@Operation(summary = "Update Users",
            description = "🔒 Accessible by all role")
public  ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody UserDto userDto){
        UserDto savedUserDto = userService.updateUser(userId,userDto);
        return ResponseEntity.ok(savedUserDto);
}

@GetMapping("/{id}")
@PreAuthorize("hasRole('ADMIN')")
@Operation(summary = "Get User By Id",
            description = "🔒 Accessible by all role")
public  ResponseEntity<UserDto> getByUserId(@PathVariable Long id){
      UserDto userDto=  userService.getByUserId(id);
      return ResponseEntity.ok(userDto);
    }

}
