package com.example.gameleaderboard.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ScoreRequest(

        @NotBlank(message = "playerName must not be blank")
        @Size(max = 40, message = "playerName must contain at most 40 characters")
        String playerName,

        @Min(value = 0, message = "score must be zero or greater")
        @Max(value = 10_000_000, message = "score must not exceed 10000000")
        Integer score,

        @NotBlank(message = "gameMode must not be blank")
        @Size(max = 30, message = "gameMode must contain at most 30 characters")
        String gameMode
) {
}