package com.member.management.service.impl;

import com.member.management.model.dto.MemberDto;
import com.member.management.model.entity.Member;
import com.member.management.repository.MemberRepository;
import com.member.management.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public void save(MemberDto memberDto) {
            Member member = new Member();
            member.setId(memberDto.getId());
            member.setName(memberDto.getName());
            member.setDepartment(memberDto.getDepartment());
            member.setMajor(memberDto.getMajor());
            member.setPhone(memberDto.getPhone());
            member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
            memberRepository.save(member);
    }

    @Override
    public String getNameById(String id) {
        return memberRepository.getNameById(id);
    }

    @Override
    public boolean checkPassword(String id, String oldPassword) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return passwordEncoder.matches(oldPassword, member.getPassword());
        }
        return false;
    }

    @Override
    public void changePassword(String id, String newPassword) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setPassword(passwordEncoder.encode(newPassword));
            memberRepository.save(member);
        }
    }
}
