package com.victornsto.restwithspringbootandjava.unittests.service;

import com.victornsto.restwithspringbootandjava.dto.v1.BookDto;
import com.victornsto.restwithspringbootandjava.model.Book;
import com.victornsto.restwithspringbootandjava.repository.BookRepository;
import com.victornsto.restwithspringbootandjava.services.BookServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {
    private final BookRepository bookRepository = mock(BookRepository.class);
    private final ConversionService conversionService = mock(ConversionService.class);
    private final BookServices bookServices = new BookServices(bookRepository, conversionService);
    BookDto bookDto = new BookDto();


    @BeforeEach
    void setUp() {
        bookDto.setId(1L);
        bookDto.setAuthor("Test Author");
        bookDto.setTitle("Test Book");
        bookDto.setLaunchDate(new java.util.Date());
        bookDto.setPrice(29.99);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setLaunchDate(new java.util.Date());
        book.setPrice(29.99);
        when(bookRepository.findAll()).thenReturn(List.of(book));
        when(conversionService.convert(any(Book.class), eq(BookDto.class))).thenReturn(bookDto);
        List<BookDto> result = bookServices.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Book", result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
        verify(conversionService, times(1)).convert(book, BookDto.class);
    }

    @Test
    void findById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setLaunchDate(new java.util.Date());
        book.setPrice(29.99);
        when(bookRepository.findById(anyLong())).thenReturn(java.util.Optional.of(book));
        when(conversionService.convert(any(Book.class), eq(BookDto.class))).thenReturn(bookDto);
        BookDto result = bookServices.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Book", result.getTitle());
        verify(bookRepository, times(2)).findById(anyLong());
        verify(conversionService, times(1)).convert(book, BookDto.class);
    }

    @Test
    void create() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setLaunchDate(new java.util.Date());
        book.setPrice(29.99);

        when(conversionService.convert(any(BookDto.class), eq(Book.class))).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(conversionService.convert(any(Book.class), eq(BookDto.class))).thenReturn(bookDto);

        BookDto result = bookServices.create(bookDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Book", result.getTitle());
        verify(conversionService, times(1)).convert(bookDto, Book.class);
        verify(bookRepository, times(1)).save(book);
        verify(conversionService, times(1)).convert(book, BookDto.class);
    }

    @Test
    void update() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Updated Book");
        book.setAuthor("Updated Author");
        book.setLaunchDate(new java.util.Date());
        book.setPrice(39.99);

        BookDto updatedBookDto = new BookDto();
        updatedBookDto.setId(1L);
        updatedBookDto.setTitle("Updated Book");
        updatedBookDto.setAuthor("Updated Author");
        updatedBookDto.setLaunchDate(new java.util.Date());
        updatedBookDto.setPrice(39.99);

        when(conversionService.convert(any(BookDto.class), eq(Book.class))).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(conversionService.convert(any(Book.class), eq(BookDto.class))).thenReturn(updatedBookDto);

        BookDto result = bookServices.update(updatedBookDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Book", result.getTitle());
        assertEquals("Updated Author", result.getAuthor());
        verify(conversionService, times(1)).convert(updatedBookDto, Book.class);
        verify(bookRepository, times(1)).save(book);
        verify(conversionService, times(1)).convert(book, BookDto.class);
    }

    @Test
    void delete() {
        Long bookId = 1L;

        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setLaunchDate(new java.util.Date());
        book.setPrice(29.99);

        when(bookRepository.findById(anyLong())).thenReturn(java.util.Optional.of(book));
        doNothing().when(bookRepository).delete(book);

        assertDoesNotThrow(() -> bookServices.delete(bookId));

        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).delete(book);
    }
}