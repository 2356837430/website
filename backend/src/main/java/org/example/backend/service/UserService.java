package org.example.backend.service;

import org.example.backend.entity.Account;
import org.example.backend.mapper.UserMapper;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Account createAccount(String username, String password) {
        userMapper.createAccount(username, password);
        return findAccountByName(username);
    }

    public Account findAccountByName(String username) {
        return userMapper.findAccountByName(username);
    }

    public boolean login(String username, String password) {
        Account account = findAccountByName(username);
        return account != null && account.getPassword().equals(password);
    }
}


