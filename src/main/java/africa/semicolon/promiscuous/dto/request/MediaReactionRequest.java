package africa.semicolon.promiscuous.dto.request;

import africa.semicolon.promiscuous.models.Reaction;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MediaReactionRequest {
    private Reaction reaction;
    private Long mediaId;
    private Long userId;
}
