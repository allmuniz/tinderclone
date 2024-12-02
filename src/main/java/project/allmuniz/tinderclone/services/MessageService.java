package project.allmuniz.tinderclone.services;

import org.springframework.stereotype.Service;
import project.allmuniz.tinderclone.dtos.MessageRequestDto;
import project.allmuniz.tinderclone.entities.Conversation;
import project.allmuniz.tinderclone.entities.Message;
import project.allmuniz.tinderclone.entities.User;
import project.allmuniz.tinderclone.repositories.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageProducer messageProducer;
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ConversationService conversationService;

    public MessageService(MessageProducer messageProducer, MessageRepository messageRepository, UserService userService, ConversationService conversationService) {
        this.messageProducer = messageProducer;
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.conversationService = conversationService;
    }

    public String sendMessage(MessageRequestDto message, Long idReceiver) {
        Optional<User> profileAuthenticate = userService.getUser();
        Optional<Conversation> conversation = conversationService.findConversation(profileAuthenticate.get().getId(), idReceiver);

        if (conversation.isPresent()) {
            Message messageToSend = new Message();
            messageToSend.setSenderId(profileAuthenticate.get().getId());
            messageToSend.setReceiverId(idReceiver);
            messageToSend.setContent(message.content());
            messageToSend.setTimestamp(LocalDateTime.now());
            messageToSend.setRead(false);
            messageToSend.setConversation(conversation.get());
            messageProducer.sendMessage(messageToSend);
            return "Menssagem enviada";
        }
        return "Mensagem não enviada";
    }

    public List<Message> findMessages(Long user2){
        Optional<User> userLogger = userService.getUser();

        Optional<Conversation> conversation = conversationService.findConversation(userLogger.get().getId(), user2);
        return messageRepository.findByConversationOrderByTimestampAsc(conversation.get());
    }




    public List<Message> getUnreadMessages(Long userId) {
        return messageRepository.findByReceiverIdAndReadFalse(userId);
    }

    public void markAsRead(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow();
        message.setRead(true);
        messageRepository.save(message);
    }
}
