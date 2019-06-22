package userservice.dto;

import lombok.Data;

@Data
public class OutgoingTokenDto {
    private String jwtToken;
    private String fullName;
}
