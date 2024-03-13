package com.ryan.spiderlab.domain;

import com.ryan.spiderlab.common.enums.BookStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    private String name;
    private String uniqueNumber;
    private Integer amount;
    private Integer numberOfRentals;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Book(Member member, String name, String uniqueNumber, Integer amount, Integer numberOfRentals, BookStatus status) {
        this.member = member;
        this.name = name;
        this.uniqueNumber = uniqueNumber;
        this.amount = amount;
        this.numberOfRentals = numberOfRentals;
        this.status = status;
    }

    public void checkIn() {
        System.out.println(11);
        this.status = BookStatus.AVAILABLE;
    }

    public void checkOut(Member member) {
        this.member = member;
        this.status = BookStatus.RENT;
        this.numberOfRentals += 1;
    }
}
