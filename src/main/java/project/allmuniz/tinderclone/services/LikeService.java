package project.allmuniz.tinderclone.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import project.allmuniz.tinderclone.entities.Like;
import project.allmuniz.tinderclone.entities.User;

import java.util.Optional;

@Service
public class LikeService {

    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;

    public LikeService(RabbitTemplate rabbitTemplate, UserService userService) {
        this.rabbitTemplate = rabbitTemplate;
        this.userService = userService;
    }

    public void sendLike(Long likedProfileId) {
        Optional<User> profileAuthenticate = userService.getUser();

        Like likeEvent = new Like(profileAuthenticate.get().getId(), likedProfileId);
        rabbitTemplate.convertAndSend("likeQueue", likeEvent);
    }
}
