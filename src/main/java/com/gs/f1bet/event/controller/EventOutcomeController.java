package com.gs.f1bet.event.controller;

import com.gs.f1bet.config.ExceptionHandlerConfig.ApiError;
import com.gs.f1bet.event.converter.EventOutcomeConverter;
import com.gs.f1bet.event.dto.EventOutcomeDto;
import com.gs.f1bet.event.dto.WinningDriver;
import com.gs.f1bet.event.service.EventOutcomeService;
import com.gs.f1bet.event.service.EventOutcomeService.EventOutcomeParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Event Outcome")
@RestController
@RequestMapping("/events/{event_key}/outcome")
@RequiredArgsConstructor
public class EventOutcomeController {

    private final EventOutcomeService eventOutcomeService;
    private final EventOutcomeConverter eventOutcomeConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Finish an event with the winning driver")
    @ApiResponse(
            responseCode = "200",
            description = "Event has been successfully finished",
            content = @Content(schema = @Schema(implementation = EventOutcomeDto.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ApiError.class))
    )
    EventOutcomeDto getEvents(
            @Schema(example = "9135")
            @PathVariable("event_key")
            String eventKey,

            @RequestBody
            WinningDriver winningDriver) {

        var params = EventOutcomeParams.builder()
                .eventKey(eventKey)
                .odd(winningDriver.odd())
                .driverNumber(winningDriver.driverNumber())
                .build();

        return eventOutcomeConverter.apply(eventOutcomeService.finish(params));
    }
}
