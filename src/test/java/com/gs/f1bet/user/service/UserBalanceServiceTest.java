package com.gs.f1bet.user.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.gs.f1bet.user.model.User;
import com.gs.f1bet.user.repository.UserRepository;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ServiceTest
@DatabaseSetup("/datasets/users.xml")
class UserBalanceServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired UserBalanceService userBalanceService;

    @Test
    void withdraw() {
        // given
        var user = userRepository.findByUsername("admin").orElse(null);
        assertThat(user).isNotNull();

        // when
        user = userBalanceService.withdraw(user, BigDecimal.valueOf(50));

        // then
        assertThat(user)
                .isNotNull()
                .extracting(User::getBalance, InstanceOfAssertFactories.BIG_DECIMAL)
                .isEqualByComparingTo(BigDecimal.valueOf(50));
    }

    @Test
    void withdrawFails() {
        // given
        var user = userRepository.findByUsername("admin").orElse(null);
        assertThat(user).isNotNull();

        // when
        var throwable = catchThrowable(() -> userBalanceService.withdraw(user, BigDecimal.valueOf(250)));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deposit() {
        // given
        var user = userRepository.findByUsername("admin").orElse(null);
        assertThat(user).isNotNull();

        // when
        user = userBalanceService.deposit(user, BigDecimal.valueOf(50));

        // then
        assertThat(user)
                .isNotNull()
                .extracting(User::getBalance, InstanceOfAssertFactories.BIG_DECIMAL)
                .isEqualByComparingTo(BigDecimal.valueOf(150));
    }

    @Test
    void depositFails() {
        // given
        var user = userRepository.findByUsername("admin").orElse(null);
        assertThat(user).isNotNull();

        // when
        var throwable = catchThrowable(() -> userBalanceService.deposit(user, BigDecimal.valueOf(-10)));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }
}