package com.grepp.jdbc.app.book.validator;

import com.grepp.jdbc.app.book.dao.BookDao;
import com.grepp.jdbc.app.book.dto.BookDto;
import com.grepp.jdbc.app.book.dto.form.RegistBookForm;
import com.grepp.jdbc.infra.db.JdbcTemplate;
import com.grepp.jdbc.infra.exception.ValidException;
import com.grepp.jdbc.infra.validator.Validator;
import java.sql.Connection;
import java.util.Optional;

public class RegistBookFormValidator implements Validator<RegistBookForm> {

  private final BookDao bookDao = new BookDao();
  private final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();

  @Override
  public void validate(RegistBookForm form) {

    Connection conn = jdbcTemplate.getConnection();
    Optional<BookDto> book = bookDao.selectByIsbn(conn, form.getBkIdx());

    // todo : 도서 등록 후 등록한 도서의 정보를 출력합니다.
    // 도서명, isbn, 작가, 카테고리는 공백일 수 없습니다.
    // 도서재고는 0보다 작을 수 없습니다.
    // 카테고리는 NOVEL, POEM, HUMANITY 가 존재합니다.

    book.ifPresent(e -> {
      throw new ValidException("중복된 도서번호 입니다.");
    });

    if(form.getBkIdx() == null || form.getBkIdx() <= 0){
      throw new ValidException("도서번호는 공백일 수 없습니다.");
    }


    if(form.getTitle() == null || form.getTitle().isBlank()){
      throw new ValidException("도서명은 공백일 수 없습니다.");
    }

    if(form.getIsbn() == null || form.getIsbn().isBlank()){
      throw new ValidException("ISBN 코드는 공백일 수 없습니다.");
    }

    if(form.getAuthor() == null || form.getAuthor().isBlank()){
      throw new ValidException("작가명은 공백일 수 없습니다.");
    }

    if(form.getCategory() == null || form.getCategory().isBlank()){
      throw new ValidException("카테고리명은 공백일 수 없습니다.");
    }

    if(!form.getCategory().equals("NOVEL") && !form.getCategory().equals("POEM")
        && !form.getCategory().equals("HUMANITY")){
      throw new ValidException("카테고리는 NOVEL, POEM, HUMANITY 중 하나여야 합니다.");
    }

    if(form.getAmount() == null || form.getAmount().length() < 0){
      throw new ValidException("도서재고는 0보다 작을 수 없습니다.");
    }

  }



}
