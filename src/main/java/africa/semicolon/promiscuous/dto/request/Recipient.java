package africa.semicolon.promiscuous.dto.request;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Recipient {
    private String name;
    @NonNull
    private String email;
}
