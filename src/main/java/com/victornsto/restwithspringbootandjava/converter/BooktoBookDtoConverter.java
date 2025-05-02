package com.victornsto.restwithspringbootandjava.converter;

import com.victornsto.restwithspringbootandjava.dto.v1.BookDto;
import com.victornsto.restwithspringbootandjava.model.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BooktoBookDtoConverter implements Converter<Book, BookDto> {
    @Override
    public BookDto convert(Book source) {
        BookDto bookDto = new BookDto();
        bookDto.setId(source.getId());
        bookDto.setAuthor(source.getAuthor());
        bookDto.setTitle(source.getTitle());
        bookDto.setLaunchDate(source.getLaunchDate());
        bookDto.setPrice(source.getPrice());
        return bookDto;
    }
}
