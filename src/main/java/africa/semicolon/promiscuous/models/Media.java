package africa.semicolon.promiscuous.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<Reaction> reactions;

    @Column(unique = true, columnDefinition = "MEDIUMTEXT", length = 1000)
    private String url;

    @ManyToOne
    private User user;

}
