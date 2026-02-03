package com.gs.f1bet.bet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record BetResponse(
        @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        @JsonProperty("bet_code")
        String betCode) {
}
