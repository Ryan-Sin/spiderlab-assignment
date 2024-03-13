package com.ryan.spiderlab.service.validator;

import com.ryan.spiderlab.common.enums.ErrorType;
import com.ryan.spiderlab.common.enums.StatusCode;
import com.ryan.spiderlab.common.exception.CommonException;
import com.ryan.spiderlab.common.exception.ErrorMessage;
import com.ryan.spiderlab.domain.Book;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookValidator {

    public Book assertNotLentAvailableBook(Optional<Book> book) {
       return book.orElseThrow(() -> CommonException.builder()
                .errorType(ErrorType.DEVELOPER)
                .status(StatusCode.FAIL.getStatusCode())
                .clientErrorMessage(ErrorMessage.NOT_AVAILABLE_RENT_BOOK)
                .build());
    }

    public void assertUniqueNumberBookExist(Optional<Book> book) {
        book.ifPresent(d -> {
            throw CommonException.builder()
                    .errorType(ErrorType.DEVELOPER)
                    .status(StatusCode.FAIL.getStatusCode())
                    .clientErrorMessage(ErrorMessage.EXIST_UNIQUE_BOOK)
                    .build();
        });
    }
}
