package com.example.gameleaderboard.repository;

import com.example.gameleaderboard.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findTop10ByOrderByScoreDescCreatedAtAsc();

    List<Score> findByPlayerNameOrderByScoreDescCreatedAtAsc(String playerName);
}