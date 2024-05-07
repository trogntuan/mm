package com.member.management.service;

import com.member.management.model.entity.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(String id);

}
