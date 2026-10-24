package com.example.gameleaderboard.dto;

import java.time.Instant;

public record ScoreResponse(
        Long id,
        String playerName,
        Integer score,
        String gameMode,
        Instant createdAt
) {
}