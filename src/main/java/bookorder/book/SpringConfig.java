package bookorder.book;

import bookorder.book.repository.BookRepository;
import bookorder.book.repository.JpaBookRepository;
import bookorder.book.repository.JpaPurchaseRepository;
import bookorder.book.repository.PurchaseReposiotory;
import bookorder.book.service.BookService;
import bookorder.book.service.PurchaseService;
import jakarta.persistence.EntityManager;
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
        return new JpaBookRepository(em);
    }

    @Bean
    public PurchaseService purchaseService() {
        return new PurchaseService(purchaseReposiotory(), bookRepository());
    }

    @Bean
    public PurchaseReposiotory purchaseReposiotory() {
        return new JpaPurchaseRepository(em);
    }

}
