package org.example.user_api.repository;

import org.example.user_api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    Page<User> findUsersByRole(String role, Pageable pageable);
}
