package com.gs.f1bet.user.service;

import com.gs.f1bet.user.model.User;
import com.gs.f1bet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {

    private final UserRepository userRepository;

    public User get() {
        var username = requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

        return userRepository
                .findByUsername(username)
                .orElseThrow();
    }
}
