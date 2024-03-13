package com.ryan.spiderlab.service;

import com.ryan.spiderlab.common.enums.BookStatus;
import com.ryan.spiderlab.domain.Book;
import com.ryan.spiderlab.repository.BookRepository;
import com.ryan.spiderlab.repository.MemberRepository;
import com.ryan.spiderlab.service.command.BookCommand;
import com.ryan.spiderlab.service.info.BookInfo;
import com.ryan.spiderlab.service.validator.BookValidator;
import com.ryan.spiderlab.service.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final MemberValidator memberValidator;
    private final BookValidator bookValidator;


    public boolean onConsignment(String email, BookCommand.ConsignmentCommand consignmentCommand) {
        var memberEntity = this.memberRepository.findByEmail(email);
        var member = this.memberValidator.assertMemberNotExist(memberEntity);

        var bookEntity = this.bookRepository.findByUniqueNumber(consignmentCommand.getUniqueNumber());
        this.bookValidator.assertUniqueNumberBookExist(bookEntity);
        this.bookRepository.register(member, consignmentCommand.getName(), consignmentCommand.getAmount(), consignmentCommand.getUniqueNumber());

        return true;
    }

    public BookInfo.BookInfoList getBookLis(String email, BookCommand.GetBookListCommand getBookListCommand) {
        var memberEntity = this.memberRepository.findByEmail(email);
        this.memberValidator.assertMemberNotExist(memberEntity);

        var bookSliceList = this.bookRepository.findBySlicePageNation(getBookListCommand.getType(), getBookListCommand.getPage(), getBookListCommand.getLimit());
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

        return BookInfo.BookInfoList.builder()
                .isNext(bookSliceList.hasNext())
                .bookList(bookInfoList)
                .build();
    }

    @Transactional
    public boolean rentBook(String email, BookCommand.RentCommand rentCommandList) {
        var memberEntity = this.memberRepository.findByEmail(email);
        var member = this.memberValidator.assertMemberNotExist(memberEntity);

        rentCommandList.getRentCommandDataList()
                .stream()
                .map(rentCommand -> {
                    var bookEntity = this.bookRepository.findByUniqueNumberAndStatus(rentCommand.getUniqueNumber(), BookStatus.AVAILABLE);
                    return this.bookValidator.assertNotLentAvailableBook(bookEntity);
                })
                .forEach(book -> this.bookRepository.rentMemberBook(member, book));

        return true;
    }

    public List<Book> getRentBookList() {
        List<Book> allByRentStatus = this.bookRepository.findAllByRentStatus();
        return allByRentStatus;
    }

    public void bulkCheckInBook(List<Book> bookList) {
        this.bookRepository.bulkCheckInBook(bookList);
    }
}
