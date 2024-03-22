package bookorder.book.service;

import static org.junit.jupiter.api.Assertions.*;

import bookorder.book.domain.Book;
import bookorder.book.repository.BookRepository;
import bookorder.book.repository.MemoryBookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BookServiceTest {

    BookService bookService;
    BookRepository bookRepository;

    @BeforeEach
    public void beforeEach() {
        bookRepository = new MemoryBookRepository();
        bookService = new BookService(bookRepository);
    }

    @Test
    void 책_추가() {
        //given
        Book book = new Book();
        book.setName("spring");
        book.setCategory("dev");
        book.setOriginPrice(20000);

        //when
        Long saveBookId = bookService.addBook(book);

        //then
        Book findBook = bookService.findOne(saveBookId).get();
        Assertions.assertThat(book.getName()).isEqualTo(findBook.getName());
    }

    @Test
    void 중복_책_예외() {
        Book book1 = new Book();
        book1.setName("spring2");

        Book book2 = new Book();
        book2.setName("spring2");

        bookService.addBook(book1);
        assertThrows(IllegalStateException.class, () -> bookService.addBook(book2));
/*
        try {
            bookService.add(book2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 책입니다.");
        }
*/

    }

    @Test
    void findBooks() {
    }

    @Test
    void findOne() {
    }
}