package com.ryan.spiderlab.controller;

import com.ryan.spiderlab.common.response.SuccessResponse;
import com.ryan.spiderlab.controller.dto.BookDto;
import com.ryan.spiderlab.controller.mapper.BookMapper;
import com.ryan.spiderlab.service.BookService;
import com.ryan.spiderlab.service.info.BookInfo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/book")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping("/consignment")
    @Operation(summary = "도서 위탁하기", description = "회원은 위탁하기 위한 자신의 도서를 등록합니다.")
    public SuccessResponse onConsignment(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid BookDto.ConsignmentRequest request
    ) {
        var email = user.getUsername();
        var bookCommand = this.bookMapper.of(request);
        var data = this.bookService.onConsignment(email, bookCommand);
        return SuccessResponse.setSuccessResponse(data);
    }

    @GetMapping("/consignment")
    @Operation(summary = "도서 조회", description = "위탁 도서로 등록된 도서 정보를 조회합니다.")
    public SuccessResponse getBookLis(
            @AuthenticationPrincipal User user,
            @Valid BookDto.GetBookListRequest request
    ) {
        var email = user.getUsername();
        var memberCommand = this.bookMapper.of(request);
        var bookInfoList = this.bookService.getBookLis(email, memberCommand);
        var data = this.bookMapper.of(bookInfoList);
        return SuccessResponse.setSuccessResponse(data);
    }
}
