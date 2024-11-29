package project.allmuniz.tinderclone.controllers;

import org.springframework.web.bind.annotation.*;
import project.allmuniz.tinderclone.dtos.ConversationRequestDto;
import project.allmuniz.tinderclone.entities.Conversation;
import project.allmuniz.tinderclone.services.ConversationService;

import java.util.Optional;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping("/")
    public Optional<Conversation> getConversation(@RequestBody ConversationRequestDto conversation) {
        return conversationService.findConversation(conversation.user1(), conversation.user2());
    }
}
