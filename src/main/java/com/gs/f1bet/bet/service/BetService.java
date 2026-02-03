package com.gs.f1bet.bet.service;

import com.gs.f1bet.bet.model.Bet;
import com.gs.f1bet.bet.repository.BetRepository;
import com.gs.f1bet.user.service.AuthenticatedUserService;
import com.gs.f1bet.user.service.UserBalanceService;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BetService {

    private final BetRepository betRepository;
    private final UserBalanceService userBalanceService;
    private final AuthenticatedUserService authenticatedUserService;

    @Transactional
    public Bet placeBet(BetOrder order) {
        var user = authenticatedUserService.get();
        var bet = Bet.builder()
                .user(user)
                .eventKey(order.eventKey())
                .driverNumber(order.driverNumber())
                .amount(order.amount())
                .build();

        userBalanceService.withdraw(user, order.amount());

        return betRepository.save(bet);
    }

    @Builder
    public record BetOrder(
            String eventKey,
            String driverNumber,
            BigDecimal amount
    ) {
    }
}
