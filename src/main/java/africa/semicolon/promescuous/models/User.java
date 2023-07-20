package africa.semicolon.promescuous.models;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private BasicData basicData;

    private LocalDate dateOfBirth;

    @OneToOne
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private Role role;


}
