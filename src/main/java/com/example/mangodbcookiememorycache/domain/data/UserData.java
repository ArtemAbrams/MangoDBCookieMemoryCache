package com.example.mangodbcookiememorycache.domain.data;

import com.example.mangodbcookiememorycache.domain.entity.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class UserData {
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters long")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters long")
    private String lastName;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private List<PostData> posts;
}
