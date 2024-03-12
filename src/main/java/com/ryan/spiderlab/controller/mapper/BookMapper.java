package com.ryan.spiderlab.controller.mapper;

import com.ryan.spiderlab.controller.dto.BookDto;
import com.ryan.spiderlab.service.command.BookCommand;
import com.ryan.spiderlab.service.info.BookInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BookMapper {
    BookCommand.ConsignmentCommand of(BookDto.ConsignmentRequest consignmentRequest);
    BookCommand.GetBookListCommand of(BookDto.GetBookListRequest getBookListRequest);

    @Mappings({
            @Mapping(source = "isNext", target = "isNext"),
            @Mapping(source = "bookList", target = "bookList"),
    })
    BookDto.GetBookListResponse of(BookInfo.BookInfoList bookInfoList);
}
