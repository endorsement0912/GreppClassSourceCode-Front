package com.grepp.jdbc.app.book;

import com.grepp.jdbc.app.book.dao.BookDao;
import com.grepp.jdbc.app.book.dto.BookDto;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.util.List;

public class BookService {

    private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    private final BookDao bookDao = new BookDao();


    public BookDto registBook(BookDto dto) {
        return null;
    }


    public List<BookDto> selectAllBook() {
        Connection conn = jdbcTemplate.getConnection();
        
        try{
            return bookDao.selectAll(conn);
        }finally {
            jdbcTemplate.close(conn);
        }
    }


    public Object updateBook(BookDto dto) {
        //구현해야함
    }
}
