package project.allmuniz.tinderclone.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import project.allmuniz.tinderclone.entities.Message;
import project.allmuniz.tinderclone.repositories.MessageRepository;

@Service
public class MessageConsumer {

    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public MessageConsumer(MessageRepository messageRepository, SimpMessagingTemplate messagingTemplate) {
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = "chatQueue")
    public void receiveMessage(Message message) {
        // Persistir a mensagem no banco
        Message messageEntity = new Message();
        messageEntity.setSenderId(message.getSenderId());
        messageEntity.setReceiverId(message.getReceiverId());
        messageEntity.setContent(message.getContent());
        messageEntity.setTimestamp(message.getTimestamp());
        messageEntity.setConversation(message.getConversation());
        messageRepository.save(messageEntity);

        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
