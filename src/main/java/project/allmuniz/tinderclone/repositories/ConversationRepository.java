package project.allmuniz.tinderclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.allmuniz.tinderclone.entities.Conversation;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("SELECT c FROM Conversation c " +
            "WHERE (c.user1.id = :user1Id AND c.user2.id = :user2Id) " +
            "   OR (c.user1.id = :user2Id AND c.user2.id = :user1Id)")
    Optional<Conversation> findByUsers(Long user1Id, Long user2Id);
}
