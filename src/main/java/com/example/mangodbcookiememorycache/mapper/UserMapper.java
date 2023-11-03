package com.example.mangodbcookiememorycache.mapper;

import com.example.mangodbcookiememorycache.domain.data.UserData;
import com.example.mangodbcookiememorycache.domain.dto.UserDTO;
import com.example.mangodbcookiememorycache.domain.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public User dataToEntity(UserData userData){
        return User.builder()
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .email(userData.getEmail())
                .posts(userData.getPosts()
                        .stream()
                        .map(PostMapper::dataToEntity)
                        .toList())
                .build();
    }
    public UserDTO entityToDTO(User user){
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .posts(user.getPosts()
                        .stream()
                        .map(PostMapper::entityToDTO)
                        .toList())
                .build();
    }
}
