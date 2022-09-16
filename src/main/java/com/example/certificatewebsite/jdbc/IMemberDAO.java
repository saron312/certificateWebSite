package com.example.certificatewebsite.jdbc;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IMemberDAO {
    @Select("select count(id) from member where id = #{id}")
    List<MemberDTO> list();
}
