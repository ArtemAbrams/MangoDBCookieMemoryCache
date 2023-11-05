package com.example.mangodbcookiememorycache.service.impl;

import com.example.mangodbcookiememorycache.domain.data.UserData;
import com.example.mangodbcookiememorycache.domain.dto.UserDTO;
import com.example.mangodbcookiememorycache.domain.entity.User;
import com.example.mangodbcookiememorycache.exception.AuthenticationFailed;
import com.example.mangodbcookiememorycache.exception.UserAlreadyExistsException;
import com.example.mangodbcookiememorycache.exception.UserWasNotFoundException;
import com.example.mangodbcookiememorycache.mapper.UserMapper;
import com.example.mangodbcookiememorycache.repository.UserRepository;
import com.example.mangodbcookiememorycache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final static String CACHE_NAME = "user";
    @Override
    @Transactional
    public UserDTO registerNewUserAccount(UserData userData) {
        if (emailExist(userData.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address: " + userData.getEmail());
        }
        User user = User
                .builder()
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .email(userData.getEmail())
                .password(passwordEncoder.encode(userData.getPassword()))
                .id(UUID.randomUUID()
                        .toString())
                .build();
        return UserMapper.entityToDTO(
                userRepository.save(user));
    }

    @Override
    public UserDTO loginAccount(String email, String password) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserWasNotFoundException("User with email" + email+ " was not found"));
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new AuthenticationFailed("Password or email is incorrect");
        }
        var authentication = new  UsernamePasswordAuthenticationToken(
                email,
                password,
                null
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return UserMapper.entityToDTO(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findUserByEmail(email)
                .isPresent();
    }
}
