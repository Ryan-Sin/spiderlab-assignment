package com.ryan.spiderlab.scheduler;

import com.ryan.spiderlab.domain.Book;
import com.ryan.spiderlab.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookScheduler {

    private final BookService bookService;

    @Scheduled(
            fixedDelay = 10000,
            initialDelay = 10000
    )
    public void run() {
        var checkInBookList = this.bookService.getRentBookList().stream()
                .peek((Book::checkIn)).toList();
        this.bookService.bulkCheckInBook(checkInBookList);
    }
}
