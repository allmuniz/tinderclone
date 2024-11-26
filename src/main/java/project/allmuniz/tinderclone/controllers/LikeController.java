package project.allmuniz.tinderclone.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.allmuniz.tinderclone.services.LikeService;

@RestController
@RequestMapping("/likes")
@Tag(name = "Likes", description = "Gerenciamento dos likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // Endpoint para enviar um "like"
    @PostMapping("/{likedProfileId}")
    @Operation(description = "Insira os dados para dar um Like",
            summary = "Criação de Like")
    public ResponseEntity<String> likeUser(@PathVariable Long likedProfileId) { // Id do usuário que vai receber o like

        try {
            likeService.sendLike(likedProfileId); // Envia o evento de like para a fila
            return ResponseEntity.ok("Like enviado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar like: " + e.getMessage());
        }
    }
}
