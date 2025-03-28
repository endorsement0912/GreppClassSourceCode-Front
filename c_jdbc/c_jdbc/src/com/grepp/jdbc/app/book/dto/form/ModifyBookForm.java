package com.grepp.jdbc.app.book.dto.form;

import com.grepp.jdbc.app.book.dto.BookDto;
import com.grepp.jdbc.app.member.dto.MemberDto;

public class ModifyBookForm { // 완?
  private Integer bkIdx;
  private String info;

  @Override
  public String toString() {
    return "ModifyBookForm{" +
        "bkIdx='" + bkIdx + '\'' +
        ", info='" + info + '\'' +
        '}';
  }

  public Integer getBkIdx() {
    return bkIdx;
  }

  public void setBkIdx(Integer bkIdx) {
    this.bkIdx = bkIdx;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public BookDto toDto(){
    BookDto dto = new BookDto();
    dto.setBkIdx(bkIdx);
    dto.setInfo(info);
    return dto;
  }
}
