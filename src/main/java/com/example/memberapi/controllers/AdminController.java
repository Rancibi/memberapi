package com.example.memberapi.controllers;

import com.example.memberapi.entities.Member;
import com.example.memberapi.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MemberService memberService;

    @Autowired
    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Hämta alla medlemmar
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // Hämta en specifik medlem
    @GetMapping("/member/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // Lägg till en ny medlem
    @PostMapping("/addmember")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member newMember = memberService.addMember(member);
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    // Uppdatera en medlem
    @PutMapping("/updatemember/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updatedMember) {
        Member member = memberService.updateMember(id, updatedMember);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // Ta bort en medlem
    @DeleteMapping("/deletemember/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/deletememberbyid")
    public String deleteMemberById(@RequestParam Long id) {
        memberService.deleteMember(id);
        return "redirect:/admin/deletemember";
    }

    // Visa alla medlemmar i Thymeleaf (localhost:8081/admin/deletemember)
    @GetMapping("/deletemember")
    public String deleteMemberPage(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "deletemember";
    }
}
