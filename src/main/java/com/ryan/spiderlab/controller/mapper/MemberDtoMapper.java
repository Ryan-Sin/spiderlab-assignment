package com.ryan.spiderlab.controller.mapper;

import com.ryan.spiderlab.controller.dto.MemberDto;
import com.ryan.spiderlab.service.command.MemberCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MemberDtoMapper {
    MemberCommand.SignUpCommand of(MemberDto.SignUpRequest signUpRequest);
    MemberCommand.SignInCommand of(MemberDto.SignInRequest signInRequest);
    MemberDto.SignInResponse of(String accessToken);
}
