package project.allmuniz.tinderclone.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Like implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long profileId;
    @Column(nullable = false)
    private Long likedProfileId;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Like(Long profileId, Long likedProfileId) {
        this.profileId = profileId;
        this.likedProfileId = likedProfileId;
        this.createdAt = LocalDateTime.now();
    }
}