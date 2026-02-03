package com.gs.f1bet.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UserBalanceDto(
        @Schema(example = "100")
        @JsonProperty("balance")
        BigDecimal balance) {
}
