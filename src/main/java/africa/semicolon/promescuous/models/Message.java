package africa.semicolon.promescuous.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long senderId;
    @ManyToOne
    private User user;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT", length = 1000)
    private String messageBody;

    private LocalDateTime time ;


}
