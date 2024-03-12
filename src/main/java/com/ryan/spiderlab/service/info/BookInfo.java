package com.ryan.spiderlab.service.info;

import com.ryan.spiderlab.common.enums.BookStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class BookInfo {

    @Getter
    @ToString
    public static class Main {
        private String memberName;
        private String name;
        private int amount;
        private String uniqueNumber;
        private BookStatus status;

        @Builder
        public Main(String memberName, String name, int amount, String uniqueNumber, BookStatus status) {
            this.memberName = memberName;
            this.name = name;
            this.amount = amount;
            this.uniqueNumber = uniqueNumber;
            this.status = status;
        }
    }

    @Getter
    @ToString
    public static class BookInfoList {
        private Boolean isNext;
        private List<BookInfo.Main> bookList;

        @Builder
        public BookInfoList(Boolean isNext, List<BookInfo.Main> bookList) {
            this.isNext = isNext;
            this.bookList = bookList;
        }
    }
}
