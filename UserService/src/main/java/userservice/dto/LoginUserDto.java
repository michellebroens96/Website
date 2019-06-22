package userservice.dto;

import lombok.Data;
import userservice.model.Scope;

@Data
public class LoginUserDto {
    private String username;
    private String password;
    private Scope scope;
}
