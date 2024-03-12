package com.ryan.spiderlab.controller;

import com.ryan.spiderlab.common.response.SuccessResponse;
import com.ryan.spiderlab.controller.dto.MemberDto;
import com.ryan.spiderlab.controller.mapper.MemberDtoMapper;
import com.ryan.spiderlab.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final MemberDtoMapper memberDtoMapper;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "신규 유저 회원가입 기능을 제공합니다.")
    public SuccessResponse onSignUp(@RequestBody @Valid MemberDto.SignUpRequest request) {
        var memberCommand = this.memberDtoMapper.of(request);
        var result = this.memberService.onSignUp(memberCommand);
        return SuccessResponse.setSuccessResponse(result);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = "신규가입을 성공한 유저는 아이디와 비밀번호를 통해 인증 토큰을 발급 받습니다.")
    public SuccessResponse onSignIn(@RequestBody @Valid MemberDto.SignInRequest request) {
        var memberCommand = this.memberDtoMapper.of(request);
        var token = this.memberService.onSignIn(memberCommand);
        var response = this.memberDtoMapper.of(token);
        return SuccessResponse.setSuccessResponse(response);
    }

}
