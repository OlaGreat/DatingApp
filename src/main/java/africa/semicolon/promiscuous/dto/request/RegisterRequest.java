package africa.semicolon.promiscuous.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
  private String email;
  private String password;
}
