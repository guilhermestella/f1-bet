package com.gs.f1bet.event.controller;

import com.gs.f1bet.config.ExceptionHandlerConfig.ApiError;
import com.gs.f1bet.event.dto.EventDto;
import com.gs.f1bet.event.service.EventService;
import com.gs.f1bet.event.service.EventService.EventParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Tag(name = "Events")
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "View List of Formula 1 Events")
    @ApiResponse(
            responseCode = "200",
            description = "Event details retrieved successfully",
            content = @Content(schema = @Schema(implementation = EventDto.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    Collection<EventDto> getEvents(
            @Schema(example = "2023")
            @RequestParam("year")
            Integer year,

            @Schema(example = "Belgium")
            @RequestParam("country_name")
            String countryName,

            @Schema(example = "Qualifying")
            @RequestParam("event_type")
            String eventType) {

        var params = EventParams.builder()
                .year(year)
                .countryName(countryName)
                .eventType(eventType)
                .build();

        return eventService.getEvents(params);
    }
}
