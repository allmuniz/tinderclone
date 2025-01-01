package project.allmuniz.tinderclone.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import project.allmuniz.tinderclone.entities.Message;

@Controller
public class WebSocketController {

    @MessageMapping("/sendMessage") // Escuta mensagens enviadas para /app/sendMessage
    @SendTo("/topic/messages") // Envia mensagens para todos inscritos no tópico /topic/messages
    public Message handleMessage(Message message) {
        return message;
    }
}
