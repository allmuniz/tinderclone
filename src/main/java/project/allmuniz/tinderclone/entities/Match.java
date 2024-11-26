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
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long profile1Id;
    @Column(nullable = false)
    private Long profile2Id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Match(Long profile1_id, Long profile2_id) {
        this.profile1Id = profile1_id;
        this.profile2Id = profile2_id;
        this.createdAt = LocalDateTime.now();
    }
}