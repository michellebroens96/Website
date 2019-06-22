package userservice.dto;

import lombok.Data;
import userservice.model.Scope;

@Data
public class OutgoingUserDto {
    private String username;
    private Scope scope;
    private String firstName;
    private String lastName;
}
