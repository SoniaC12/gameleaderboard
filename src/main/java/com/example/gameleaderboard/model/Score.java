package com.example.gameleaderboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String playerName;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false, length = 30)
    private String gameMode;

    @Column(nullable = false)
    private Instant createdAt;

    protected Score() {
    }

    public Score(String playerName, Integer score, String gameMode, Instant createdAt) {
        this.playerName = playerName;
        this.score = score;
        this.gameMode = gameMode;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getScore() {
        return score;
    }

    public String getGameMode() {
        return gameMode;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}