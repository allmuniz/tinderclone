package project.allmuniz.tinderclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.allmuniz.tinderclone.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
