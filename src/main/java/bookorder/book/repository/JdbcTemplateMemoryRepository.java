package bookorder.book.repository;

import bookorder.book.domain.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcTemplateMemoryRepository implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Book save(Book book) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("book").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", book.getName());
        parameters.put("category", book.getCategory());
        parameters.put("originPrice", book.getOriginPrice());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        book.setId(key.longValue());
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        List<Book> result = jdbcTemplate.query("select * from book where id = ?", bookRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Book> findByName(String name) {
        List<Book> result = jdbcTemplate.query("selet * from book where name = ?", bookRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("select * from book", bookRowMapper());
    }

    private RowMapper<Book> bookRowMapper() {
        return (rs, rowNum) -> {

            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setName(rs.getString("name"));
            book.setCategory(rs.getString("category"));
            book.setOriginPrice(rs.getInt("originPrice"));

            return book;
        };
    }
}
