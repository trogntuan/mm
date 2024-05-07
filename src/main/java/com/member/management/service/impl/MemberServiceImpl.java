package com.member.management.service.impl;

import com.member.management.model.entity.Member;
import com.member.management.repository.MemberRepository;
import com.member.management.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }
}
