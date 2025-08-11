// src/main/java/io/github/faedorodolfo/learning_spring_boot/infrastructure/security/service/UserDetailsServiceImpl.java
package com.rodolfocf.expenses_api.infrastructure.security;

import com.rodolfocf.expenses_api.infrastructure.entities.User;
import com.rodolfocf.expenses_api.infrastructure.exceptions.ResourceNotFoundException;
import com.rodolfocf.expenses_api.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação do UserDetailsService que carrega o usuário do banco de dados.
 * Usado pela autenticação JWT e padrão do Spring Security.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email "  + email + " nor found"));

        // Aqui você pode futuramente mapear roles reais do usuário
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .authorities("ROLE_USER") // padrão: prefixo "ROLE_"
                .build();
    }
}
