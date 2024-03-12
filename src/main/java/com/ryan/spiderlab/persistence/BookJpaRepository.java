package com.ryan.spiderlab.persistence;

import com.ryan.spiderlab.domain.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookJpaRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book as b left join fetch Member as m on b.member = m order by b.createdAt desc")
    Slice<Book> findBookByHighNumberOfRentalsSlice(Pageable pageRequest);

    @Query("select b from Book as b left join fetch Member as m on b.member = m order by b.amount asc")
    Slice<Book> findBookByLowAmountSlice(Pageable pageRequest);

    @Query("select b from Book as b left join fetch Member as m on b.member = m order by b.numberOfRentals desc")
    Slice<Book> findBookByCreatedAtSlice(Pageable pageRequest);
}
