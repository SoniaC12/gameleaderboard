package com.example.gameleaderboard.dto;

public record VersionResponse(
        String application,
        String version,
        String status
) {
}