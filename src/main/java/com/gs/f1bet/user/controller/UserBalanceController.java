package com.gs.f1bet.user.controller;

import com.gs.f1bet.user.dto.UserBalanceDto;
import com.gs.f1bet.user.service.AuthenticatedUserService;
import com.gs.f1bet.user.service.UserBalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Balance")
@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class UserBalanceController {

    private final UserBalanceService userBalanceService;
    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve current user balance")
    @ApiResponse(
            responseCode = "200",
            description = "User balance retrieved",
            content = @Content(schema = @Schema(implementation = UserBalanceDto.class)))
    UserBalanceDto getBalance() {
        var user = authenticatedUserService.get();
        var balance = userBalanceService.balance(user);

        return UserBalanceDto.builder()
                .balance(balance)
                .build();
    }
}
