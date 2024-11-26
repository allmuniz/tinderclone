package project.allmuniz.tinderclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.allmuniz.tinderclone.entities.Like;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByProfileIdAndLikedProfileId(Long profileId, Long likedProfileId);
}
