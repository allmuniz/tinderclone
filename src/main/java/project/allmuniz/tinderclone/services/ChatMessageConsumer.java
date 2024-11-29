package project.allmuniz.tinderclone.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import project.allmuniz.tinderclone.entities.Message;
import project.allmuniz.tinderclone.repositories.MessageRepository;

@Service
public class ChatMessageConsumer {

    private final MessageRepository messageRepository;

    public ChatMessageConsumer(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
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
    }
}
