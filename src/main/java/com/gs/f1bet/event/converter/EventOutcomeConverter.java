package com.gs.f1bet.event.converter;

import com.gs.f1bet.event.dto.EventOutcomeDto;
import com.gs.f1bet.event.model.EventOutcome;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EventOutcomeConverter implements Function<EventOutcome, EventOutcomeDto> {

    @Override
    @Transactional
    public EventOutcomeDto apply(EventOutcome eventOutcome) {
        return EventOutcomeDto.builder()
                .eventKey(eventOutcome.getEventKey())
                .driverNumber(eventOutcome.getDriverNumber())
                .winners(eventOutcome.getWinningBets().size())
                .losers(eventOutcome.getLosingBets().size())
                .build();
    }
}
