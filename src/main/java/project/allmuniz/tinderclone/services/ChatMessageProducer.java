package project.allmuniz.tinderclone.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import project.allmuniz.tinderclone.entities.Message;

@Service
public class ChatMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ChatMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Message message) {
        rabbitTemplate.convertAndSend("chatQueue", message);
    }
}
