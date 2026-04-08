package com.satish.dto;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Long roleId;
    private Boolean isActive;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
