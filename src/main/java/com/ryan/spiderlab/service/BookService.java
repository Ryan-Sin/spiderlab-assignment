package com.ryan.spiderlab.service;

import com.ryan.spiderlab.repository.BookRepository;
import com.ryan.spiderlab.repository.MemberRepository;
import com.ryan.spiderlab.service.command.BookCommand;
import com.ryan.spiderlab.service.info.BookInfo;
import com.ryan.spiderlab.service.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final MemberValidator memberValidator;


    public boolean onConsignment(String email, BookCommand.ConsignmentCommand consignmentCommand) {
        var memberEntity = this.memberRepository.findByEmail(email);
        var member = this.memberValidator.assertMemberNotExist(memberEntity);

        this.bookRepository.register(member, consignmentCommand.getName(), consignmentCommand.getAmount(), consignmentCommand.getUniqueNumber());

        return true;
    }

    public BookInfo.BookInfoList getBookLis(String email, BookCommand.GetBookListCommand getBookListCommand) {
        var memberEntity = this.memberRepository.findByEmail(email);
        var member = this.memberValidator.assertMemberNotExist(memberEntity);

        var bookSliceList = this.bookRepository.findBookBySlicePageNation(member, getBookListCommand.getType(), getBookListCommand.getPage(), getBookListCommand.getLimit());
        var bookInfoList = bookSliceList.stream()
                .map((book) -> BookInfo.Main.builder()
                        .memberName(book.getMember().getName())
                        .name(book.getName())
                        .uniqueNumber(book.getUniqueNumber())
                        .amount(book.getAmount())
                        .status(book.getStatus())
                        .build()
                )
                .collect(Collectors.toList());

        System.out.println("bookInfoList = " + bookInfoList);

        return BookInfo.BookInfoList.builder()
                .isNext(bookSliceList.hasNext())
                .bookList(bookInfoList)
                .build();
    }
}
