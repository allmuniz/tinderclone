package project.allmuniz.tinderclone.services;

import org.springframework.stereotype.Service;
import project.allmuniz.tinderclone.entities.Conversation;
import project.allmuniz.tinderclone.repositories.ConversationRepository;

import java.util.Optional;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public Optional<Conversation> findConversation(Long user1, Long user2) {
        return conversationRepository.findByUsers(user1, user2);
    }
}
