package com.example.gameleaderboard.controller;

import com.example.gameleaderboard.dto.LeaderboardEntryResponse;
import com.example.gameleaderboard.dto.ScoreRequest;
import com.example.gameleaderboard.dto.ScoreResponse;
import com.example.gameleaderboard.dto.VersionResponse;
import com.example.gameleaderboard.service.ScoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ScoreController {

    private final ScoreService scoreService;
    private final String applicationVersion;

    public ScoreController(
            ScoreService scoreService,
            @Value("${application.version}") String applicationVersion
    ) {
        this.scoreService = scoreService;
        this.applicationVersion = applicationVersion;
    }

    @PostMapping("/scores")
    @ResponseStatus(HttpStatus.CREATED)
    public ScoreResponse submitScore(@Valid @RequestBody ScoreRequest request) {
        return scoreService.submitScore(request);
    }

    @GetMapping("/leaderboard")
    public List<LeaderboardEntryResponse> getLeaderboard() {
        return scoreService.getLeaderboard();
    }

    @GetMapping("/players/{playerName}")
    public List<ScoreResponse> getScoresByPlayerName(@PathVariable String playerName) {
        return scoreService.getScoresByPlayerName(playerName);
    }

    @GetMapping("/version")
    public VersionResponse getVersion() {
        return new VersionResponse(
                "game-leaderboard-api",
                applicationVersion,
                "UP"
        );
    }
}