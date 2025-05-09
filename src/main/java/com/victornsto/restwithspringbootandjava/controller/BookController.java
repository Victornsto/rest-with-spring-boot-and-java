package com.victornsto.restwithspringbootandjava.controller;

import com.victornsto.restwithspringbootandjava.controller.docs.BookControllerDocs;
import com.victornsto.restwithspringbootandjava.dto.v1.BookDto;
import com.victornsto.restwithspringbootandjava.services.BookServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<BookDto>> getBooks(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        Sort.Direction sort = "asc".equalsIgnoreCase(direction) ? Sort.Direction.ASC : Sort.Direction.DESC;
        return ResponseEntity.ok(bookServices.findAll(PageRequest.of(page, size, Sort.by(sort, "author"))));
    }

    @GetMapping(value = "/byTitle/{title}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/yaml"})
    @Override
    public ResponseEntity<Page<BookDto>> getBooksByTitle(
            @PathVariable("title") String title,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction)
             {
        Sort.Direction sort = "asc".equalsIgnoreCase(direction) ? Sort.Direction.ASC : Sort.Direction.DESC;
        return ResponseEntity.ok(bookServices.findAllByTitle(title ,PageRequest.of(page, size, Sort.by(sort, "author"))));
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
