package org.example.user_api.service;

import org.example.user_api.dto.UserRequestDto;
import org.example.user_api.dto.UserResponseDto;
import org.example.user_api.model.User;
import org.example.user_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        if(userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered.");
        }
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setRole(userRequestDto.getRole());
        User savedUser = userRepository.save(user);
        return mapToResponseDto(savedUser);
    }

    @Override
    public Page<UserResponseDto> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);

        return new PageImpl<>(
                userPage.getContent().stream()
                        .map(this::mapToResponseDto)
                        .collect(Collectors.toList()),
                pageable,
                userPage.getTotalElements()
        );
    }

    @Override
    public Page<UserResponseDto> searchUsersByRole(String role, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findUsersByRole(role, pageable);
        return new PageImpl<>(
                userPage.getContent().stream().map(this::mapToResponseDto).collect(Collectors.toList()),
                pageable,
                userPage.getTotalElements()
        );
    }

    private UserResponseDto mapToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
