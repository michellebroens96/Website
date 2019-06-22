package userservice.dto;

import lombok.Data;
import userservice.model.Scope;

@Data
public class CreateUserDto {
    private String username;
    private String password;
    private Scope scope;
    private String firstName;
    private String lastName;
}
