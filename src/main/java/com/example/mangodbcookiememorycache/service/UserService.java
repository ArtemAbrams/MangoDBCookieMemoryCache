package com.example.mangodbcookiememorycache.service;

import com.example.mangodbcookiememorycache.domain.data.UserData;
import com.example.mangodbcookiememorycache.domain.dto.UserDTO;
import com.example.mangodbcookiememorycache.domain.entity.User;

public interface UserService {
    User registerNewUserAccount(UserData userData);

    UserDTO loginAccount(String email, String password);
}
