package com.ryan.spiderlab.service.command;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class MemberCommand {

    @Getter
    @Builder
    @ToString
    public static class SignUpCommand {
        private String name;
        private String email;
        private String password;
        private String phone;
    }

    @Getter
    @Builder
    @ToString
    public static class SignInCommand {
        private String email;
        private String password;
    }
}
