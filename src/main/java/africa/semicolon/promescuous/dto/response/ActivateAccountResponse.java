package africa.semicolon.promescuous.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ActivateAccountResponse {
    private String message;
    private GetUserResponse user;
}
