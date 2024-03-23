package bookorder.book.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import bookorder.book.domain.Book;
import bookorder.book.repository.BookRepository;
import bookorder.book.repository.MemoryBookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BookServiceIntegrationTest {

    @Autowired BookService bookService;
    @Autowired BookRepository bookRepository;

    @Test
    void 책_추가() {
        //given
        Book book = new Book();
        book.setName("spring123");
        book.setCategory("dev");
        book.setOriginPrice(20000);

        //when
        Long saveId = bookService.addBook(book);

        //then
        Book findBook = bookService.findOne(saveId).get();
        Assertions.assertThat(book.getName()).isEqualTo(findBook.getName());
    }

    @Test
    void 중복_책_예외() {
        Book book1 = new Book();
        book1.setName("spring2");
        book1.setCategory("dev");
        book1.setOriginPrice(20000);

        Book book2 = new Book();
        book2.setName("spring2");
        book2.setCategory("develop");
        book2.setOriginPrice(30000);

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