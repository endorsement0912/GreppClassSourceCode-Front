package com.grepp.jdbc;

import com.grepp.jdbc.member.code.Grade;
import com.grepp.jdbc.member.dao.MemberDao;
import com.grepp.jdbc.member.dto.MemberDto;

// NOTE 01 JDBC API
// JDBC : Java DataBase Connectivity
// API : Application Programing Interface
public class Run {

  public static void main(String[] args) {
    MemberDao dao = new MemberDao();
    //insert(dao);
    select(dao);
  }

  private static void select(MemberDao dao) {
    System.out.println(dao.selectByIDAndPassowrd("super", "9999"));
  }

  private static void insert(MemberDao dao) {
    MemberDto dto = new MemberDto();
    dto.setUserId("super");
    dto.setPassword("9999");
    dto.setEmail("super@gmail.com");
    dto.setTell("010-2222-3333");
    dto.setLeave(false);
    dto.setGrade(Grade.ROLE_ADMIN);

    System.out.println(dao.insert(dto));
  }

}
