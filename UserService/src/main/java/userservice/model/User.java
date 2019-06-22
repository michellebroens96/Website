package userservice.model;

import database.model.BaseEntity;
import lombok.Data;

@Data
public class User extends BaseEntity {
    private String username;
    private String password;
    private Scope scope;
    private String firstName;
    private String lastName;

    public String getShortFullName() {
        return firstName.substring(0,1) + ", " + lastName;
    }

    public String getFullName() {
        return lastName + ", " + firstName;
    }
}
