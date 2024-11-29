package project.allmuniz.tinderclone.controllers;

import org.springframework.web.bind.annotation.*;
import project.allmuniz.tinderclone.dtos.ConversationRequestDto;
import project.allmuniz.tinderclone.dtos.MessageRequestDto;
import project.allmuniz.tinderclone.entities.Message;
import project.allmuniz.tinderclone.services.ChatService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send/{idReceiver}")
    public String sendMessage(@RequestBody MessageRequestDto message, @PathVariable Long idReceiver) {
        return chatService.sendMessage(message, idReceiver);
    }

    @PostMapping("/")
    public List<Message> getMessages(@RequestBody ConversationRequestDto conversation) {
        return chatService.buscaMenssagensConversaTest(conversation.user1(), conversation.user2());
    }




    //INATIVOS
    @GetMapping("/unread/{userId}")
    public List<Message> getUnreadMessages(@PathVariable Long userId) {
        return chatService.getUnreadMessages(userId);
    }

    @PatchMapping("/read/{messageId}")
    public void markAsRead(@PathVariable Long messageId) {
        chatService.markAsRead(messageId);
    }
}
