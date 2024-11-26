package project.allmuniz.tinderclone.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.allmuniz.tinderclone.dtos.UserRequestDto;
import project.allmuniz.tinderclone.enums.StatusProfile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_users")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusProfile status;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public User(UserRequestDto userRequest, String password) {
        this.name = userRequest.name();
        this.email = userRequest.email();
        this.password = password;
        this.phone = userRequest.phone();
        this.birthday = userRequest.birthday();
        this.address = userRequest.address();
        this.status = StatusProfile.ACTIVE;
        this.roles = Collections.singletonList("ROLE_USER");
    }
}
