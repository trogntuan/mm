package com.member.management.security;

import com.member.management.model.entity.Member;
import com.member.management.service.MemberService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberService memberService;

    public UserDetailsServiceImpl(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String id)
            throws UsernameNotFoundException {
        Optional<Member> memberOptional = memberService.findById(id);
        if (memberOptional.isEmpty()) {
            throw new UsernameNotFoundException("Account: \"" + id + "\" is not exist");
        }
        Member member = memberOptional.get();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(member.getId(), member.getPassword(), authorities);
    }
}
