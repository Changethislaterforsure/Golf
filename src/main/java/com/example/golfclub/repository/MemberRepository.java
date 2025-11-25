package com.example.golfclub.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.golfclub.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByMemberNameContainingIgnoreCase(String memberName);

    List<Member> findByMembershipTypeIgnoreCase(String membershipType);

    List<Member> findByMemberPhoneContaining(String memberPhone);

    @Query("SELECT DISTINCT m FROM Member m JOIN m.tournaments t WHERE t.startDate = :startDate")
    List<Member> findByTournamentStartDate(LocalDate startDate);
}
