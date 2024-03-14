package com.ryan.spiderlab.controller.dto;

import com.ryan.spiderlab.common.enums.BookFilterType;
import com.ryan.spiderlab.common.enums.BookStatus;
import com.ryan.spiderlab.common.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

public class BookDto {

    @Getter
    @Builder
    @ToString
    @Schema(title = "도서 위탁 요청 정보")
    public static class ConsignmentRequest {
        @Schema(description = "도서명", example = "세이노의 가르침")
        private String name;

        @Schema(description = "ISBN", example = "9791168473690")
        private String uniqueNumber;

        @Schema(description = "대여가격", example = "1500")
        private int amount;
    }

    @Getter
    @Builder
    @ToString
    @Schema(title = "등록된 위타 도서 조회하기")
    public static class GetBookListRequest {
        @Schema(description = "조회 조건", example = "Ex- HIGH_NUMBER_OF_RENTALS: 대여 많은순, LOW_AMOUNT: 가격 낮은 순, CREATED_AT: 최근 등고순")
        private BookFilterType type;

        @Min(value = 0, message = ErrorMessage.INCORRECT_FORMAT_PAGE)
        @Schema(description = "검색 페이지", example = "0")
        private int page;

        @Min(value = 0, message = ErrorMessage.INCORRECT_FORMAT_LIMIT)
        @Schema(description = "최대 조회 수", example = "20")
        private int limit;
    }

    @Getter
    @Builder
    @ToString
    public static class GetBookListResponse {
        @Schema(description = "다음 페이지 존재 여부", example = "true")
        public Boolean isNext;
        @Schema(description = "도서 정보")
        public List<GetBookResponse> bookList;
    }

    @Getter
    @Builder
    @ToString
    public static class GetBookResponse {
        @Schema(description = "위탁자 이름")
        private String memberName;
        @Schema(description = "도서 이름")
        private String name;
        @Schema(description = "도서 가격")
        private int amount;
        @Schema(description = "ISBN 정보")
        private String uniqueNumber;
        @Schema(description = "도서 위탁 상태")
        private BookStatus status;
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class RentBookRequest {
        @Schema(description = "회원이 선택한 위탁 도서 정보")
        public List<RentBookData> rentBookDataList;
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class RentBookData {
        @NotBlank(message = ErrorMessage.REQUIRED_VALUE_UNIQUE)
        @Schema(description = "ISBN 정보")
        public String uniqueNumber;

        @Schema(description = "도서 가격")
        public int amount;
    }
}
