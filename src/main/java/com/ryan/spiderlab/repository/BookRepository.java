package com.ryan.spiderlab.repository;

import com.ryan.spiderlab.common.enums.BookFilterType;
import com.ryan.spiderlab.common.enums.BookStatus;
import com.ryan.spiderlab.domain.Book;
import com.ryan.spiderlab.domain.Member;
import com.ryan.spiderlab.persistence.BookJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final BookJpaRepository bookJpaRepository;
    public void register(Member member, String name, int amount, String uniqueNumber) {
        Book book = Book.builder()
                .member(member)
                .name(name)
                .uniqueNumber(uniqueNumber)
                .amount(amount)
                .status(BookStatus.AVAILABLE)
                .numberOfRentals(0)
                .build();
        this.bookJpaRepository.save(book);
    }

    public Slice<Book> findBySlicePageNation(BookFilterType type, int page, int limit) {
        switch (type) {
            case CREATED_AT:
                Pageable createdAtPage = PageRequest.of(page, limit);
                return this.bookJpaRepository.findBookByCreatedAtSlice(createdAtPage);
            case LOW_AMOUNT:
                Pageable amountPage = PageRequest.of(page, limit);
                return this.bookJpaRepository.findBookByLowAmountSlice(amountPage);
            default:
                Pageable highRentalPage = PageRequest.of(page, limit);
                return this.bookJpaRepository.findBookByHighNumberOfRentalsSlice(highRentalPage);
        }
    }

    public Optional<Book> findByUniqueNumberAndStatus(String uniqueNumber, BookStatus status) {
       return this.bookJpaRepository.findByUniqueNumberAndStatus(uniqueNumber, status);
    }

    public void rentMemberBook(Member member, Book book) {
        book.checkOut(member);
        this.bookJpaRepository.save(book);
    }

    public Optional<Book> findByUniqueNumber(String uniqueNumber) {
        return this.bookJpaRepository.findByUniqueNumber(uniqueNumber);
    }

    public List<Book> findAllByRentStatus() {
        return this.bookJpaRepository.findAllByStatus(BookStatus.RENT);
    }

    public void bulkCheckInBook(List<Book> bookList) {
        this.bookJpaRepository.saveAll(bookList);
    }
}
