package africa.semicolon.promiscuous.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.AUTO;

@Getter
@Setter
public class MediaReaction {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private Long user;
    @Enumerated(value = STRING)
    private Reaction reaction;
}
