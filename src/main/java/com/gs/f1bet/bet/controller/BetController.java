package com.gs.f1bet.bet.controller;

import com.gs.f1bet.bet.dto.BetRequest;
import com.gs.f1bet.bet.dto.BetResponse;
import com.gs.f1bet.bet.service.BetService;
import com.gs.f1bet.bet.service.BetService.BetOrder;
import com.gs.f1bet.config.ExceptionHandlerConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bets")
@RestController
@RequestMapping("/bets")
@RequiredArgsConstructor
public class BetController {

    private final BetService betService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Place a bet for the driver")
    @ApiResponse(
            responseCode = "200",
            description = "Bet has been successfully added",
            content = @Content(schema = @Schema(implementation = BetResponse.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ExceptionHandlerConfig.ApiError.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(implementation = ExceptionHandlerConfig.ApiError.class))
    )
    BetResponse postBet(@RequestBody BetRequest request) {
        var order = BetOrder.builder()
                .eventKey(request.eventKey())
                .driverNumber(request.driverNumber())
                .amount(request.amount())
                .build();

        var bet = betService.placeBet(order);
        return BetResponse.builder()
                .betCode(bet.getCode().toString())
                .build();
    }
}
