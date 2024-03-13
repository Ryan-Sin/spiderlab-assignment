package com.ryan.spiderlab.controller.dto;

import com.ryan.spiderlab.common.enums.BookFilterType;
import com.ryan.spiderlab.common.enums.BookStatus;
import com.ryan.spiderlab.common.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
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
    public static class GetBookListRequest {
        private BookFilterType type;

        @Min(value = 0, message = ErrorMessage.INCORRECT_FORMAT_PAGE)
        private int page;

        @Min(value = 0, message = ErrorMessage.INCORRECT_FORMAT_LIMIT)
        private int limit;
    }

    @Getter
    @Builder
    @ToString
    public static class GetBookListResponse {
        public Boolean isNext;
        public List<GetBookResponse> bookList;
    }

    @Getter
    @Builder
    @ToString
    public static class GetBookResponse {
        private String memberName;
        private String name;
        private int amount;
        private String uniqueNumber;
        private BookStatus status;
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class RentBookRequest {
        public List<RentBookData> rentBookDataList;
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class RentBookData {
        public String uniqueNumber;
        public int amount;
    }
}
