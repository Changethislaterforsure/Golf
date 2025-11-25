package com.example.golfclub.service;

import com.example.golfclub.entity.Member;
import com.example.golfclub.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public List<Member> searchByName(String name) {
        return memberRepository.findByMemberNameContainingIgnoreCase(name);
    }

    public List<Member> searchByMembershipType(String type) {
        return memberRepository.findByMembershipTypeIgnoreCase(type);
    }

    public List<Member> searchByPhone(String phone) {
        return memberRepository.findByMemberPhoneContaining(phone);
    }

    public List<Member> searchByTournamentStartDate(LocalDate startDate) {
        return memberRepository.findByTournamentStartDate(startDate);
    }
}
