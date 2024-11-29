package project.allmuniz.tinderclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.allmuniz.tinderclone.entities.Conversation;
import project.allmuniz.tinderclone.entities.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverIdAndReadFalse(Long receiverId);
    List<Message> findByConversationOrderByTimestampAsc(Conversation conversation);
}
