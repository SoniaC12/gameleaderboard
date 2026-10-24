package com.example.gameleaderboard;

import com.example.gameleaderboard.dto.LeaderboardEntryResponse;
import com.example.gameleaderboard.dto.ScoreRequest;
import com.example.gameleaderboard.dto.ScoreResponse;
import com.example.gameleaderboard.repository.ScoreRepository;
import com.example.gameleaderboard.service.ScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:leaderboard;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class ScoreServiceTest {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoreRepository scoreRepository;

    @BeforeEach
    void cleanDatabase() {
        scoreRepository.deleteAll();
    }

    @Test
    void leaderboardShouldSortScoresInDescendingOrder() {
        scoreService.submitScore(new ScoreRequest("PlayerOne", 850, "ranked"));
        scoreService.submitScore(new ScoreRequest("PlayerTwo", 1200, "ranked"));
        scoreService.submitScore(new ScoreRequest("PlayerThree", 1000, "ranked"));

        List<LeaderboardEntryResponse> leaderboard = scoreService.getLeaderboard();

        assertThat(leaderboard).hasSize(3);
        assertThat(leaderboard.get(0).playerName()).isEqualTo("PlayerTwo");
        assertThat(leaderboard.get(0).score()).isEqualTo(1200);
        assertThat(leaderboard.get(0).rank()).isEqualTo(1);
        assertThat(leaderboard.get(1).playerName()).isEqualTo("PlayerThree");
        assertThat(leaderboard.get(1).rank()).isEqualTo(2);
    }

    @Test
    void shouldStoreAndRetrievePlayerScores() {
        ScoreResponse createdScore = scoreService.submitScore(
                new ScoreRequest("TestPlayer", 1500, "ranked")
        );

        List<ScoreResponse> playerScores = scoreService.getScoresByPlayerName("TestPlayer");

        assertThat(createdScore.id()).isNotNull();
        assertThat(playerScores).hasSize(1);
        assertThat(playerScores.get(0).score()).isEqualTo(1500);
    }
}