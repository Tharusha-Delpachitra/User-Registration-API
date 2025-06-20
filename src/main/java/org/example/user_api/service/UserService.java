package org.example.user_api.service;

import org.example.user_api.dto.UserRequestDto;
import org.example.user_api.dto.UserResponseDto;
import org.springframework.data.domain.Page;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
    Page<UserResponseDto> getUsers(int page, int size);
    Page<UserResponseDto> searchUsersByRole(String role, int page, int size);
}
