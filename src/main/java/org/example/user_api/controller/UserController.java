package org.example.user_api.controller;

import org.example.user_api.dto.UserRequestDto;
import org.example.user_api.dto.UserResponseDto;
import org.example.user_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDto registerUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.registerUser(userRequestDto);
    }

    @GetMapping
    public Page<UserResponseDto> getUsers(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return userService.getUsers(page, size);
    }

    @GetMapping("/search")
    public Page<UserResponseDto> searchUsersByRole(@RequestParam String role,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        return userService.searchUsersByRole(role.toUpperCase(), page, size);
    }
}
