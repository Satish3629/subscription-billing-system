package com.satish.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class LoginOutputDto {

    private String message;
    private String token;

}
