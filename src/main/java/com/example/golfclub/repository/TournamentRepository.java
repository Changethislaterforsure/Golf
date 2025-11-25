package com.example.golfclub.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.golfclub.entity.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> findByStartDate(LocalDate startDate);

    List<Tournament> findByLocationContainingIgnoreCase(String location);
}
