package com.example.golfclub.controller;

import com.example.golfclub.entity.Member;
import com.example.golfclub.service.MemberService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member saved = memberService.createMember(member);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/search/by-name")
    public ResponseEntity<List<Member>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(memberService.searchByName(name));
    }

    @GetMapping("/search/by-membership-type")
    public ResponseEntity<List<Member>> searchByMembershipType(@RequestParam String type) {
        return ResponseEntity.ok(memberService.searchByMembershipType(type));
    }

    @GetMapping("/search/by-phone")
    public ResponseEntity<List<Member>> searchByPhone(@RequestParam String phone) {
        return ResponseEntity.ok(memberService.searchByPhone(phone));
    }

    @GetMapping("/search/by-tournament-start-date")
    public ResponseEntity<List<Member>> searchByTournamentStartDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(memberService.searchByTournamentStartDate(date));
    }
}
