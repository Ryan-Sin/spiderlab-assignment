package com.ryan.spiderlab.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "book_shelf")
public class BookShelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookShelfId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="memberId")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookId")
    private List<Book> book = new ArrayList<>();

    @Builder
    public BookShelf(Member member, List<Book> book) {
        this.member = member;
        this.book = book;
    }
}
