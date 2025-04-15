package com.example.memberapi.services;

import com.example.memberapi.entities.Member;
import com.example.memberapi.exceptions.ResourceNotFoundException;
import com.example.memberapi.repositories.MemberRepository;
import com.example.memberapi.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, AddressRepository addressRepository) {
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
    }

    // Hämta alla medlemmar
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Hämta en specifik medlem
    public Member getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new ResourceNotFoundException("Medlem med id " + id + " finns inte.");
        }
    }

    // Lägg till en ny medlem
    public Member addMember(Member member) {
        // Se till att adressen finns i databasen
        if (addressRepository.existsById(member.getAddress().getId())) {
            return memberRepository.save(member);
        } else {
            throw new ResourceNotFoundException("Adress finns inte.");
        }
    }

    // Uppdatera en existerande medlem
    public Member updateMember(Long id, Member updatedMember) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member existingMember = member.get();
            existingMember.setFirstName(updatedMember.getFirstName());
            existingMember.setLastName(updatedMember.getLastName());
            existingMember.setAddress(updatedMember.getAddress());
            existingMember.setEmail(updatedMember.getEmail());
            existingMember.setPhone(updatedMember.getPhone());
            existingMember.setDateOfBirth(updatedMember.getDateOfBirth());
            return memberRepository.save(existingMember);
        } else {
            throw new ResourceNotFoundException("Medlem med id " + id + " finns inte.");
        }
    }

    // Ta bort en medlem
    public void deleteMember(Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Medlem med id " + id + " finns inte.");
        }
    }
}
