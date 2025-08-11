package com.rodolfocf.expenses_api.infrastructure.repositories;

import com.rodolfocf.expenses_api.business.dto.UserResponseDTO;
import com.rodolfocf.expenses_api.infrastructure.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
