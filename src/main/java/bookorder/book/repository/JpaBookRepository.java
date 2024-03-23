package bookorder.book.repository;

import bookorder.book.domain.Book;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class JpaBookRepository implements BookRepository {

    private final EntityManager em;

    public JpaBookRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        Book book = em.find(Book.class, id);
        return Optional.ofNullable(book);
    }

    @Override
    public Optional<Book> findByName(String name) {
        List<Book> result = em.createQuery("select b from Book b where b.name = :name", Book.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }
}
