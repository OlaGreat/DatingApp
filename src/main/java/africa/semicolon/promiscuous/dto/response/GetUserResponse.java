package africa.semicolon.promiscuous.dto.response;

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
    private String address;
    private String phoneNumber;
    private String profileImage;
}
