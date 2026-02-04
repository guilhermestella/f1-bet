package com.gs.f1bet.event.service;

import com.gs.f1bet.bet.repository.BetRepository;
import com.gs.f1bet.event.model.EventOutcome;
import com.gs.f1bet.event.repository.EventOutcomeRepository;
import com.gs.f1bet.user.service.UserBalanceService;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.stream.Collectors.partitioningBy;

@Service
@RequiredArgsConstructor
public class EventOutcomeService {

    private final BetRepository betRepository;
    private final UserBalanceService userBalanceService;
    private final EventOutcomeRepository eventOutcomeRepository;

    @Transactional
    public EventOutcome finish(EventOutcomeParams params) {
        var odd = params.odd();
        var eventKey = params.eventKey();
        var driverNumber = params.driverNumber();

        var betsPartitionedByWinningDriver = betRepository.findByEventKey(eventKey)
                .stream()
                .collect(partitioningBy(b -> Objects.equals(b.getDriverNumber(), driverNumber)));

        var outcome = EventOutcome.builder()
                .eventKey(eventKey)
                .driverNumber(driverNumber)
                .winningBets(betsPartitionedByWinningDriver.get(true))
                .losingBets(betsPartitionedByWinningDriver.get(false))
                .build();

        for (var winningBet : outcome.getWinningBets()) {
            var amount = winningBet.getAmount();
            var user = winningBet.getUser();
            userBalanceService.deposit(user, amount.multiply(BigDecimal.valueOf(odd)));
        }

        return eventOutcomeRepository.save(outcome);
    }

    @Builder
    public record EventOutcomeParams(
            String eventKey,
            String driverNumber,
            Integer odd) {
    }
}
