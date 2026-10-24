package com.example.gameleaderboard.service;

import com.example.gameleaderboard.dto.LeaderboardEntryResponse;
import com.example.gameleaderboard.dto.ScoreRequest;
import com.example.gameleaderboard.dto.ScoreResponse;
import com.example.gameleaderboard.model.Score;
import com.example.gameleaderboard.repository.ScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public ScoreResponse submitScore(ScoreRequest request) {
        Score score = new Score(
                request.playerName().trim(),
                request.score(),
                request.gameMode().trim().toLowerCase(),
                Instant.now()
        );

        Score savedScore = scoreRepository.save(score);
        return toScoreResponse(savedScore);
    }

    @Transactional(readOnly = true)
    public List<LeaderboardEntryResponse> getLeaderboard() {
        List<Score> scores = scoreRepository.findTop10ByOrderByScoreDescCreatedAtAsc();

        return scores.stream()
                .map(score -> new LeaderboardEntryResponse(
                        scores.indexOf(score) + 1,
                        score.getPlayerName(),
                        score.getScore(),
                        score.getGameMode(),
                        score.getCreatedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ScoreResponse> getScoresByPlayerName(String playerName) {
        return scoreRepository.findByPlayerNameOrderByScoreDescCreatedAtAsc(playerName.trim())
                .stream()
                .map(this::toScoreResponse)
                .toList();
    }

    private ScoreResponse toScoreResponse(Score score) {
        return new ScoreResponse(
                score.getId(),
                score.getPlayerName(),
                score.getScore(),
                score.getGameMode(),
                score.getCreatedAt()
        );
    }
}