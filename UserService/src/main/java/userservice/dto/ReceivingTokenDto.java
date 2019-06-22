package userservice.dto;

import lombok.Data;
import userservice.model.Scope;

@Data
public class ReceivingTokenDto {
    private String token;
    private Scope scope;
}
