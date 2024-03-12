package com.ryan.spiderlab.service.command;

import com.ryan.spiderlab.common.enums.BookFilterType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class BookCommand {

    @Getter
    @Builder
    @ToString
    public static class ConsignmentCommand {
        private String name;
        private String uniqueNumber;
        private int amount;
    }

    @Getter
    @Builder
    @ToString
    public static class GetBookListCommand {
        private BookFilterType type;
        private int page;
        private int limit;
    }
}
