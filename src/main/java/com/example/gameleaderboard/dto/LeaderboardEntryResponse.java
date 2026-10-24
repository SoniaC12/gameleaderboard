package com.example.gameleaderboard.dto;

import java.time.Instant;

public record LeaderboardEntryResponse(
        Integer rank,
        String playerName,
        Integer score,
        String gameMode,
        Instant createdAt
) {
}