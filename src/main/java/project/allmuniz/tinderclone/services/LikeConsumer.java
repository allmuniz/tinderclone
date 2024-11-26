package project.allmuniz.tinderclone.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import project.allmuniz.tinderclone.entities.Like;
import project.allmuniz.tinderclone.entities.Match;
import project.allmuniz.tinderclone.repositories.LikeRepository;
import project.allmuniz.tinderclone.repositories.MatchRepository;

import java.util.Optional;

@Service
public class LikeConsumer {

    private final LikeRepository likeRepository;
    private final MatchRepository matchRepository;

    public LikeConsumer(LikeRepository likeRepository, MatchRepository matchRepository) {
        this.likeRepository = likeRepository;
        this.matchRepository = matchRepository;
    }

    @RabbitListener(queues = "likeQueue")
    public void processLike(Like likeEvent) {
        Long userId = likeEvent.getProfileId();
        Long likedUserId = likeEvent.getLikedProfileId();

        // Salva o like no banco de dados
        likeRepository.save(new Like(userId, likedUserId));

        // Verifica se há um like recíproco
        Optional<Like> reciprocalLike = likeRepository.findByProfileIdAndLikedProfileId(likedUserId, userId);

        if (reciprocalLike.isPresent()) {
            // Cria o match no banco
            matchRepository.save(new Match(userId, likedUserId));
            notifyUsersOfMatch(userId, likedUserId); // Notifica ambos os usuários
        }
    }

    private void notifyUsersOfMatch(Long profileId, Long likedProfileId) {
        // Implemente a lógica de notificação aqui (e-mail, push notification, etc.)
        System.out.println("Match encontrado entre " + profileId + " e " + likedProfileId);
    }
}