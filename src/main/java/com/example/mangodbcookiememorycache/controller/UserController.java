package com.example.mangodbcookiememorycache.controller;

import com.example.mangodbcookiememorycache.domain.data.UserData;
import com.example.mangodbcookiememorycache.domain.dto.ResponseDTO;
import com.example.mangodbcookiememorycache.mapper.UserMapper;
import com.example.mangodbcookiememorycache.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<?>> create(@RequestBody UserData userData){
       try {
          var user = userRepository.save(UserMapper.dataToEntity(
                  userData
          ));
          return ResponseEntity.status(HttpStatus.OK)
                  .body(ResponseDTO.builder()
                          .status(HttpStatus.OK)
                          .data(UserMapper.entityToDTO(user))
                          .build());
       }
       catch (Exception exception){
           log.error(exception.getMessage());
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(ResponseDTO.builder()
                           .status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .data(exception.getMessage())
                           .build());
       }
    }
}
