package org.example.user_api.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String name;
    private String email;
    private String role;
}
