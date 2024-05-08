package com.member.management.service;

import com.member.management.model.dto.MemberDto;
import com.member.management.model.entity.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(String id);
    void save(MemberDto memberDto);
    String getNameById(String id);
    boolean checkPassword(String id, String oldPassword);
    void changePassword(String id, String newPassword);
}
