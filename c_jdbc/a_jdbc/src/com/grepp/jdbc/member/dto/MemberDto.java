package com.grepp.jdbc.member.dto;

import com.grepp.jdbc.member.code.Grade;
import java.util.Objects;

public class MemberDto {
  private  String userId;
  private String password;
  private String email;
  private Grade grade;
  private String tell;
  private Boolean isLeave;

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MemberDto memberDto)) {
      return false;
    }
    return Objects.equals(userId, memberDto.userId) && Objects.equals(password,
        memberDto.password) && Objects.equals(email, memberDto.email)
        && Objects.equals(grade, memberDto.grade) && Objects.equals(tell,
        memberDto.tell) && Objects.equals(isLeave, memberDto.isLeave);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, password, email, grade, tell, isLeave);
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Grade getGrade() {
    return grade;
  }

  public void setGrade(Grade grade) {
    this.grade = grade;
  }

  public String getTell() {
    return tell;
  }

  public void setTell(String tell) {
    this.tell = tell;
  }

  public Boolean getLeave() {
    return isLeave;
  }

  public void setLeave(Boolean leave) {
    isLeave = leave;
  }
}
