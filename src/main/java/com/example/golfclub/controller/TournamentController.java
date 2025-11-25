package com.example.golfclub.controller;

import com.example.golfclub.entity.Member;
import com.example.golfclub.entity.Tournament;
import com.example.golfclub.service.TournamentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
        Tournament saved = tournamentService.createTournament(tournament);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        return ResponseEntity.ok(tournamentService.getAllTournaments());
    }

    @GetMapping("/search/by-start-date")
    public ResponseEntity<List<Tournament>> searchByStartDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(tournamentService.searchByStartDate(date));
    }

    @GetMapping("/search/by-location")
    public ResponseEntity<List<Tournament>> searchByLocation(@RequestParam String location) {
        return ResponseEntity.ok(tournamentService.searchByLocation(location));
    }

    @PostMapping("/{tournamentId}/members/{memberId}")
    public ResponseEntity<Tournament> addMemberToTournament(@PathVariable Long tournamentId,
                                                            @PathVariable Long memberId) {
        return tournamentService.addMemberToTournament(tournamentId, memberId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<Set<Member>> getMembersInTournament(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .map(t -> ResponseEntity.ok(t.getParticipants()))
                .orElse(ResponseEntity.notFound().build());
    }
}
