package com.victornsto.restwithspringbootandjava.services;

import com.victornsto.restwithspringbootandjava.controller.BookController;
import com.victornsto.restwithspringbootandjava.dto.v1.BookDto;
import com.victornsto.restwithspringbootandjava.exceptions.ResourceNotFoudException;
import com.victornsto.restwithspringbootandjava.model.Book;
import com.victornsto.restwithspringbootandjava.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    final private BookRepository repository;
    final private ConversionService conversionService;
    private final Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    public BookServices(BookRepository repository, ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }
    
    public List<BookDto> findAll() {
        logger.info("Finding all books!");
        List<BookDto> dto = repository.findAll()
                .stream()
                .map(book -> conversionService.convert(book, BookDto.class))
                .toList();
        dto.forEach(BookServices::addHateoasLinks);
        return dto;
    }

    public BookDto findById(Long id) {
        logger.info("Finding book by id: {}", id);
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoudException("Book not found"));
        BookDto dto = conversionService.convert(book, BookDto.class);
        assert dto != null;
        addHateoasLinks(dto);
        return dto;
    }

    public BookDto create(BookDto bookDto) {
        logger.info("Creating book: {}", bookDto);
        return getBookDto(bookDto);
    }


    public BookDto update(BookDto bookDto) {
        logger.info("Updating book: {}", bookDto);
        return getBookDto(bookDto);
    }

    public void delete(Long id) {
        logger.info("Deleting book by id: {}", id);
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoudException("Book not found"));
        repository.delete(book);
    }

    private static void addHateoasLinks(BookDto bookDto) {
        bookDto.add(linkTo(methodOn(BookController.class).getBookById(bookDto.getId())).withSelfRel());
        bookDto.add(linkTo(methodOn(BookController.class).getBooks()).withRel("findAll"));
        bookDto.add(linkTo(methodOn(BookController.class).createBook(bookDto)).withRel("create"));
        bookDto.add(linkTo(methodOn(BookController.class).updateBook(bookDto)).withRel("update"));
        bookDto.add(linkTo(methodOn(BookController.class).deleteBook(bookDto.getId())).withRel("delete"));
    }


    private BookDto getBookDto(BookDto bookDto) {
        var book = conversionService.convert(bookDto, Book.class);
        assert book != null;
        var savedBook = repository.save(book);
        var dto = conversionService.convert(savedBook, BookDto.class);
        assert dto != null;
        addHateoasLinks(dto);
        return dto;
    }


}
