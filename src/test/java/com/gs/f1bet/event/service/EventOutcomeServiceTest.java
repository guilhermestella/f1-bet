package com.gs.f1bet.event.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.gs.f1bet.event.service.EventOutcomeService.EventOutcomeParams;
import com.gs.f1bet.user.model.User;
import com.gs.f1bet.user.repository.UserRepository;
import com.gs.f1bet.user.service.ServiceTest;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ServiceTest
@DatabaseSetup({"/datasets/users.xml", "/datasets/bets.xml"})
class EventOutcomeServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired EventOutcomeService eventOutcomeService;

    @Test
    void finish() {
        // Given
        var params = EventOutcomeParams.builder()
                .odd(2)
                .eventKey("1")
                .driverNumber("1")
                .build();

        // When
        var outcome = eventOutcomeService.finish(params);

        // Then
        assertThat(outcome.getWinningBets()).hasSize(1);
        assertThat(outcome.getLosingBets()).hasSize(2);

        User winner = userRepository.findById(1L).orElse(null);
        assertThat(winner)
                .isNotNull()
                .extracting(User::getBalance, InstanceOfAssertFactories.BIG_DECIMAL)
                .isEqualByComparingTo(BigDecimal.valueOf(260));
    }
}