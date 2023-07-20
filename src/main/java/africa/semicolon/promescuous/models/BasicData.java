package africa.semicolon.promescuous.models;

import jakarta.persistence.*;

@Entity
public class BasicData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastName;

    private String firstName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;
}
