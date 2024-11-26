package project.allmuniz.tinderclone.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.allmuniz.tinderclone.dtos.UserResponseDto;
import project.allmuniz.tinderclone.entities.Match;
import project.allmuniz.tinderclone.services.MatchService;

import java.util.List;

@RestController
@RequestMapping("/match")
@Tag(name = "Match", description = "Combination Management")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Match>> getMatches() {
        List<Match> matches = matchService.findMatchesByProfileId();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<UserResponseDto>> getProfileMatches() {
        List<UserResponseDto> profiles = matchService.profileMatchByProfileIdDto();
        return ResponseEntity.ok(profiles);
    }
}
