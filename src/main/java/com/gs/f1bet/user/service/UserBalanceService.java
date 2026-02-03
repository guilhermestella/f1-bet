package com.gs.f1bet.user.service;

import com.gs.f1bet.user.model.User;
import com.gs.f1bet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserBalanceService {

    private final UserRepository userRepository;

    public User deposit(User user, BigDecimal amount) {
        user.deposit(amount);
        return userRepository.save(user);
    }

    public User withdraw(User user, BigDecimal amount) {
        user.withdraw(amount);
        return userRepository.save(user);
    }

    public BigDecimal balance(User user) {
        return user.getBalance();
    }
}
