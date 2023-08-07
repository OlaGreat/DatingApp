package africa.semicolon.promescuous.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
  private String email;
  private String password;
}
