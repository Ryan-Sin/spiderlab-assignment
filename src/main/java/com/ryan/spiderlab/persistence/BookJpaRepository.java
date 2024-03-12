package com.ryan.spiderlab.persistence;

import com.ryan.spiderlab.domain.Book;
import com.ryan.spiderlab.domain.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookJpaRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book as b left join fetch Member as m on b.member = :member order by b.createdAt desc")
    Slice<Book> findBookByHighNumberOfRentalsSlice(@Param("member") Member member, Pageable pageRequest);

    @Query("select b from Book as b left join fetch Member as m on b.member = :member order by b.amount asc")
    Slice<Book> findBookByLowAmountSlice(@Param("member") Member member, Pageable pageRequest);

    @Query("select b from Book as b left join fetch Member as m on b.member = :member order by b.numberOfRentals desc")
    Slice<Book> findBookByCreatedAtSlice(@Param("member") Member member, Pageable pageRequest);
}
