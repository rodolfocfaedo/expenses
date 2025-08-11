package com.rodolfocf.expenses_api.business;

import com.rodolfocf.expenses_api.business.converter.UserMapper;
import com.rodolfocf.expenses_api.business.dto.UserRequestDTO;
import com.rodolfocf.expenses_api.business.dto.UserResponseDTO;
import com.rodolfocf.expenses_api.infrastructure.entities.User;
import com.rodolfocf.expenses_api.infrastructure.exceptions.ResourceNotFoundException;
import com.rodolfocf.expenses_api.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        User userEntity = userMapper.toUserEntity(userRequestDTO);
        User savedUser = userRepository.saveAndFlush(userEntity);
        return userMapper.toUserDTO(savedUser);
    }

    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));
        return userMapper.toUserDTO(user);
    }
}