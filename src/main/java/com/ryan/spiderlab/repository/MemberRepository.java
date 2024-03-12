package com.ryan.spiderlab.repository;

import com.ryan.spiderlab.domain.Member;
import com.ryan.spiderlab.persistence.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final MemberJpaRepository memberJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Member> findByEmail(String email) {
        return this.memberJpaRepository.findByEmail(email);
    }

    public Member save(String email, String password, String name, String phone) {
        Member member = Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phone(phone)
                .build();

        return this.memberJpaRepository.save(member);
    }
}
