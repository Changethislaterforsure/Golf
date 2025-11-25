package com.example.golfclub.service;

import com.example.golfclub.entity.Member;
import com.example.golfclub.entity.Tournament;
import com.example.golfclub.repository.MemberRepository;
import com.example.golfclub.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final MemberRepository memberRepository;

    public TournamentService(TournamentRepository tournamentRepository,
                             MemberRepository memberRepository) {
        this.tournamentRepository = tournamentRepository;
        this.memberRepository = memberRepository;
    }

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public List<Tournament> searchByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public List<Tournament> searchByLocation(String location) {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    public Optional<Tournament> addMemberToTournament(Long tournamentId, Long memberId) {
        Optional<Tournament> tournamentOpt = tournamentRepository.findById(tournamentId);
        Optional<Member> memberOpt = memberRepository.findById(memberId);

        if (tournamentOpt.isEmpty() || memberOpt.isEmpty()) {
            return Optional.empty();
        }

        Tournament tournament = tournamentOpt.get();
        Member member = memberOpt.get();

        Set<Member> participants = tournament.getParticipants();
        participants.add(member);
        tournament.setParticipants(participants);

        member.getTournaments().add(tournament);

        tournamentRepository.save(tournament);
        memberRepository.save(member);

        return Optional.of(tournament);
    }
}
