package africa.semicolon.promiscuous.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Setter
@Getter
@ToString
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long senderId;
    @ManyToOne
    private User user;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT", length = 1000)
    private String messageBody;
    private String sentAt;

    @PrePersist
    private void setSendAt(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        sentAt = currentTime.format(formatter);
    }


}
