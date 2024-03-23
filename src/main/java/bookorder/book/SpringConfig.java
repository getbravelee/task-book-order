package bookorder.book;

import bookorder.book.repository.BookRepository;
import bookorder.book.repository.JdbcTemplateMemoryRepository;
import bookorder.book.repository.JpaBookRepository;
import bookorder.book.service.BookService;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository());
    }

    @Bean
    public BookRepository bookRepository() {
//        return new JdbcTemplateMemoryRepository(dataSource);
        return new JpaBookRepository(em);
    }
}
