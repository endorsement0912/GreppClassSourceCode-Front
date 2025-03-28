package com.grepp.jdbc.app.book.dao;

import com.grepp.jdbc.app.book.dto.BookDto;
import com.grepp.jdbc.infra.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao {

    public List<BookDto> selectAll(Connection conn) {
        List<BookDto> books = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("select * from book")) {
            try(ResultSet rs = ps.executeQuery()) {

                while(rs.next()){
                    BookDto book = new BookDto();
                    book.setBkIdx(rs.getInt("bk_idx"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setRegDate(rs.getObject("reg_date", LocalDateTime.class));
                    books.add(book);
                }

                return books;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<BookDto> selectByIsbn(Connection conn, Integer bkIdx) {
        String sql = "select * from book where bkIdx = ?";
        BookDto res = null;
        try(
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
          stmt.setString(1, bkIdx.toString());
          try(ResultSet rset = stmt.executeQuery()) {
              while(rset.next()){
                  res = generateBookDto(rset);
              }
              return Optional.ofNullable(res);
          }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    private BookDto generateBookDto(ResultSet rset) throws SQLException {
        BookDto res = new BookDto();
        res.setBkIdx(rset.getInt("bk_idx"));
        res.setIsbn(rset.getString("isbn"));
        res.setTitle(rset.getString("title"));
        res.setAuthor(rset.getString("author"));
        res.setInfo(rset.getString("info"));
        res.setAmount(rset.getString("book_amt"));
        res.setRegDate(rset.getObject("reg_date", LocalDateTime.class));
        res.setRentCnt(rset.getInt("rent_cnt"));

        return res;
    }

    public Optional<BookDto> insert(Connection conn, BookDto dto) {
        String sql = "insert into book(bk_idx, isbn, title, author, info, book_amt, reg_date, rent_cnt) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,dto.getBkIdx());
            stmt.setString(2,dto.getIsbn());
            stmt.setString(3,dto.getTitle());
            stmt.setString(4,dto.getAuthor());
            stmt.setString(5,dto.getInfo());
            stmt.setString(6,dto.getAmount());
            stmt.setObject(8,dto.getRegDate());
            stmt.setInt(9,dto.getRentCnt());

            int res = stmt.executeUpdate();
            return res > 0 ? Optional.of(dto) : Optional.empty();

        } catch (SQLException e) {
          throw new DataAccessException(e.getMessage(), e);
        }
    }
}