package com.ryan.spiderlab.persistence;

import com.ryan.spiderlab.domain.BookShelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookShelfJpaRepository extends JpaRepository<BookShelf, Long> {
}
