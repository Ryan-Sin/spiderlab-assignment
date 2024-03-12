package com.ryan.spiderlab.service;

import com.ryan.spiderlab.config.jwt.TokenProvider;
import com.ryan.spiderlab.repository.MemberRepository;
import com.ryan.spiderlab.service.command.MemberCommand;
import com.ryan.spiderlab.service.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    private final TokenProvider tokenProvider;

    public boolean onSignUp(MemberCommand.SignUpCommand signUpCommand) {
        var memberEntity = this.memberRepository.findByEmail(signUpCommand.getEmail());
        memberValidator.assertMemberExist(memberEntity);
        this.memberRepository.save(
                signUpCommand.getEmail(),
                signUpCommand.getPassword(),
                signUpCommand.getName(),
                signUpCommand.getPhone()
        );
        return true;
    }

    public String onSignIn(MemberCommand.SignInCommand signInCommand) {
        var memberEntity = this.memberRepository.findByEmail(signInCommand.getEmail());
        var member = memberValidator.assertMemberNotExist(memberEntity);
        this.memberValidator.assertPasswordMissMatches(signInCommand.getPassword(), member.getPassword());
        return this.tokenProvider.createToken(String.format("%s:%s", member.getEmail(), member.getName()));
    }
}
