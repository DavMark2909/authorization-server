package authorization.dto;

import lombok.Data;

import java.util.List;

public record CreateUserDto (
     String name,
     String lastname,
     String username,
     String password,
     List<String> roles
){}

