package com.example.mangodbcookiememorycache.domain.dto;

import com.example.mangodbcookiememorycache.domain.data.PostData;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private List<PostDTO> posts;
}
