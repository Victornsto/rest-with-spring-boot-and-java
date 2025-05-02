package com.victornsto.restwithspringbootandjava.controller;

import com.victornsto.restwithspringbootandjava.controller.docs.BookControllerDocs;
import com.victornsto.restwithspringbootandjava.dto.v1.BookDto;
import com.victornsto.restwithspringbootandjava.unittests.service.BookServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController implements BookControllerDocs {
    private final BookServices bookServices;
    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"})
    @Override
    public List<BookDto> getBooks() {
        return bookServices.findAll();
    }

    @GetMapping(value ="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"})
    @Override
    public BookDto getBookById(@PathVariable(value = "id") Long id) {
        return bookServices.findById(id);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"})
    @Override
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookServices.create(bookDto);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"})
    @Override
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookServices.update(bookDto);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteBook(@PathVariable(value = "id") Long id) {
        bookServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
