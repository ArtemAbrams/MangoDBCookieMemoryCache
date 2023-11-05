package com.example.mangodbcookiememorycache.service;

import com.example.mangodbcookiememorycache.domain.data.UserData;
import com.example.mangodbcookiememorycache.domain.dto.UserDTO;


public interface UserService {
    UserDTO registerNewUserAccount(UserData userData);
    UserDTO loginAccount(String email, String password);

}
