package org.example.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.example.backend.entity.Account;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO account(username, password) VALUES(#{username}, #{password})")
    void createAccount(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM account WHERE username = #{username}")
    Account findAccountByName(@Param("username") String username);
}


