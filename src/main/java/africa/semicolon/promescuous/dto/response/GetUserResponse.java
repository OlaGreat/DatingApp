package africa.semicolon.promescuous.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class GetUserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String phoneNumber;
}
