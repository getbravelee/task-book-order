package bookorder.book.repository;

import bookorder.book.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryBookRepository implements BookRepository {

    private static Map<Long, Book> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Book save(Book book) {
        book.setId(++sequence);
        store.put(book.getId(), book);
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Book> findByName(String name) {
        return store.values().stream()
                .filter(book -> book.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    // 테스트 간의 독립성을 보장하기 위해서 테스트 시에 메모리 초기화
    public void clearStore() {
        store.clear();
    }
}
