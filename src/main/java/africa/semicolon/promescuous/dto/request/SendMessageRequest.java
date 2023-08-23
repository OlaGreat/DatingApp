package africa.semicolon.promescuous.dto.request;

import africa.semicolon.promescuous.models.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class SendMessageRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long senderId;
    @ManyToOne
    private User user;
    private String messageContent;
}
