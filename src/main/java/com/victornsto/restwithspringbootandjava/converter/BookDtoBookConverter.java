package com.victornsto.restwithspringbootandjava.converter;

import com.victornsto.restwithspringbootandjava.dto.v1.BookDto;
import com.victornsto.restwithspringbootandjava.model.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookDtoBookConverter implements Converter<BookDto, Book> {
    @Override
    public Book convert(BookDto source) {
        Book book = new Book();
        book.setId(source.getId());
        book.setAuthor(source.getAuthor());
        book.setTitle(source.getTitle());
        book.setLaunchDate(source.getLaunchDate());
        book.setPrice(source.getPrice());
        return book;
    }
}
