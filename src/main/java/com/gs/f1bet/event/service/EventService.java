package com.gs.f1bet.event.service;

import com.gs.f1bet.event.client.OpenF1Client;
import com.gs.f1bet.event.converter.EventDtoConverter;
import com.gs.f1bet.event.dto.EventDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class EventService {

    private final OpenF1Client openF1Client;
    private final EventDtoConverter eventDtoConverter;

    @Cacheable("EventCache")
    public Collection<EventDto> getEvents(EventParams params) {
        return openF1Client
                .getSessions(
                        params.year(),
                        params.countryName(),
                        params.eventType())
                .stream()
                .map(session -> Pair.of(session, openF1Client.getDrivers(session.sessionKey())))
                .map(eventDtoConverter)
                .toList();
    }

    @Builder
    public record EventParams(
            Integer year,
            String countryName,
            String eventType) {
    }
}
