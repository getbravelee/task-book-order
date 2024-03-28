package bookorder.book.repository;

import static org.assertj.core.api.Assertions.assertThat;

import bookorder.book.domain.Book;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: TDD에 대해 알아보고 장/단 정리.
public class MemoryBookRepositoryTest {
    MemoryBookRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryBookRepository();
    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void 저장() {
        // given
        Book book = new Book();
        book.setName("spring");
        book.setCategory("dev");
        book.setOriginPrice(2000);

        // when
        repository.save(book);

        // then
        Book result = repository.findByName(book.getName()).get();
        assertThat(book.getId()).isNotNull();
        assertThat(result).isEqualTo(book);
    }

    @Test
    void findByName() {
        Book book1 = new Book();
        book1.setName("spring1");
        repository.save(book1);

        Book book2 = new Book();
        book2.setName("spring2");
        repository.save(book2);

        Book result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(book1);
    }

    @Test
    void findALL() {
        Book book1 = new Book();
        book1.setName("spring1");
        repository.save(book1);

        Book book2 = new Book();
        book2.setName("spring2");
        repository.save(book2);

        List<Book> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}
