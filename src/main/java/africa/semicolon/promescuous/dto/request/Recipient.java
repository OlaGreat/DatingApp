package africa.semicolon.promescuous.dto.request;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Recipient {
    private String name;
    @NonNull
    private String email;
}
