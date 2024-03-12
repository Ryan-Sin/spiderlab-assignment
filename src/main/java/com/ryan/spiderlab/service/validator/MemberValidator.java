package com.ryan.spiderlab.service.validator;

import com.ryan.spiderlab.common.enums.ErrorType;
import com.ryan.spiderlab.common.enums.StatusCode;
import com.ryan.spiderlab.common.exception.CommonException;
import com.ryan.spiderlab.common.exception.ErrorMessage;
import com.ryan.spiderlab.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberValidator {
    private final PasswordEncoder passwordEncoder;

    public void assertMemberExist(Optional<Member> user) {
        user.ifPresent(d -> {
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.EXIST_MEMBER)
                    .build();
        });
    }

    public Member assertMemberNotExist(Optional<Member> member) {
        return member.orElseThrow(() -> CommonException.builder()
                .errorType(ErrorType.DEVELOPER)
                .status(StatusCode.FAIL.getStatusCode())
                .clientErrorMessage(ErrorMessage.NOT_EXIST_MEMBER)
                .build());
    }

    public void assertPasswordMissMatches(String newPassword, String oldPassword) {
        if (!passwordEncoder.matches(newPassword, oldPassword))
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.PASSWORD_NOT_MATCH)
                    .build();
    }

}
