package project.allmuniz.tinderclone.services;

import org.springframework.stereotype.Service;
import project.allmuniz.tinderclone.dtos.UserResponseDto;
import project.allmuniz.tinderclone.entities.Match;
import project.allmuniz.tinderclone.entities.User;
import project.allmuniz.tinderclone.repositories.MatchRepository;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final UserService userService;

    public MatchService(MatchRepository matchRepository, UserService userService) {
        this.matchRepository = matchRepository;
        this.userService = userService;
    }

    public List<Match> findMatchesByProfileId(String profileEmail) {
        User profileEntity = userService.findByEmail(profileEmail);
        return matchRepository.findByProfile1IdOrProfile2Id(profileEntity.getId(), profileEntity.getId());
    }

    public List<UserResponseDto> profileMatchByProfileIdDto(String profileEmail) {
        User profileEntity = userService.findByEmail(profileEmail);
        return matchRepository.findMatchedProfilesAsDto(profileEntity.getId());
    }
}
