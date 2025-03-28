package com.grepp.jdbc.app.book;

import com.google.gson.Gson;
import com.grepp.jdbc.app.book.dto.form.ModifyBookForm;
import com.grepp.jdbc.app.book.dto.form.RegistBookForm;
import com.grepp.jdbc.app.book.validator.ModifyBookFormValidator;
import com.grepp.jdbc.app.book.validator.RegistBookFormValidator;
import com.grepp.jdbc.infra.json.GsonProvider;


public class BookController {
    private final RegistBookFormValidator registBookFormValidator = new RegistBookFormValidator();
    private final ModifyBookFormValidator modifyBookFormValidator = new ModifyBookFormValidator();



    private final BookService bookService = new BookService();
    private final Gson gson = GsonProvider.get();

    // todo : 모든 도서 정보를 출력합니다.
    public String findAllBooks() {
        return gson.toJson(bookService.selectAllBook());
    }

    // todo : 도서 등록 후 등록한 도서의 정보를 출력합니다.
    // 도서명, isbn, 작가, 카테고리는 공백일 수 없습니다.
    // 도서재고는 0보다 작을 수 없습니다.
    // 카테고리는 NOVEL, POEM, HUMANITY 가 존재합니다.
    public String registBook(RegistBookForm form) {
        registBookFormValidator.validate(form);
        return gson.toJson(bookService.registBook(form.toDto()));
    }

    // todo : 도서 소개를 수정한 후 수정된 도서의 정보를 출력합니다.
    public String modifyBookInfo(Integer bkidx, String info) {
        ModifyBookForm form = new ModifyBookForm();
        form.setBkIdx(bkidx);
        form.setInfo(info);
        modifyBookFormValidator.validate(form);

        return gson.toJson(bookService.updateBook(form.toDto()));
    }

    // todo : 도서 삭제 후 삭제 성공여부를 출력합니다.
    public String deleteBook(String bkIdx) {
        return null;
    }
}
