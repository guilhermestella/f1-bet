package com.gs.f1bet.event.converter;

import com.gs.f1bet.event.client.OpenF1Driver;
import com.gs.f1bet.event.client.OpenF1Session;
import com.gs.f1bet.event.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class EventDtoConverter
        implements Function<Pair<OpenF1Session, Collection<OpenF1Driver>>, EventDto> {

    private final DriverDtoConverter driverDtoConverter;

    @Override
    public EventDto apply(Pair<OpenF1Session, Collection<OpenF1Driver>> sessionWithDrivers) {
        var session = sessionWithDrivers.getFirst();
        var drivers = sessionWithDrivers.getSecond();

        return EventDto.builder()
                .eventKey(session.sessionKey())
                .eventType(session.sessionType())
                .eventName(session.sessionName())
                .year(session.year())
                .location(session.location())
                .dateEnd(session.dateEnd())
                .dateStart(session.dateStart())
                .countryName(session.countryName())
                .drivers(drivers.stream().map(driverDtoConverter).toList())
                .build();
    }
}
