package project.allmuniz.tinderclone.controllers;

import org.springframework.web.bind.annotation.*;
import project.allmuniz.tinderclone.dtos.MessageRequestDto;
import project.allmuniz.tinderclone.entities.Message;
import project.allmuniz.tinderclone.services.MessageService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send/{idReceiver}")
    public String sendMessage(@RequestBody MessageRequestDto message, @PathVariable Long idReceiver) {
        return messageService.sendMessage(message, idReceiver);
    }

    @GetMapping("/{receiverId}")
    public List<Message> getMessages(@PathVariable Long receiverId) {
        return messageService.findMessages(receiverId);
    }




    //INATIVOS
    @GetMapping("/unread/{userId}")
    public List<Message> getUnreadMessages(@PathVariable Long userId) {
        return messageService.getUnreadMessages(userId);
    }

    @PatchMapping("/read/{messageId}")
    public void markAsRead(@PathVariable Long messageId) {
        messageService.markAsRead(messageId);
    }
}
