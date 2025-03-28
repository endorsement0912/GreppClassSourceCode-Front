package com.grepp.jdbc.app.book.validator;

import com.grepp.jdbc.app.book.dao.BookDao;
import com.grepp.jdbc.app.book.dto.BookDto;
import com.grepp.jdbc.app.book.dto.form.ModifyBookForm;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.validator.Validator;
import java.sql.Connection;
import java.util.Optional;

public class ModifyBookFormValidator implements Validator<ModifyBookForm> {
  private final BookDao bookDao = new BookDao();
  private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();

  @Override
  public void validate(ModifyBookForm form) {
    Connection conn = jdbcTemplate.getConnection();
    Optional<BookDto> book = bookDao.selectByIsbn(conn, form.getBkIdx());

    // 구현해야함
  }
}
