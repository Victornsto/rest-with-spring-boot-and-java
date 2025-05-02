package com.victornsto.restwithspringbootandjava.repository;

import com.victornsto.restwithspringbootandjava.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
