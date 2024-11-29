package project.allmuniz.tinderclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.allmuniz.tinderclone.dtos.UserResponseDto;
import project.allmuniz.tinderclone.entities.Match;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByProfile1IdOrProfile2Id(Long profile1_id, Long profile2_id);

    @Query("SELECT new project.allmuniz.tinderclone.dtos.UserResponseDto(p.id, p.name, p.phone, p.address) " +
            "FROM User p " +
            "WHERE p.id IN (" +
            "SELECT m.profile1Id FROM Match m WHERE m.profile2Id = :profileId " +
            "UNION " +
            "SELECT m.profile2Id FROM Match m WHERE m.profile1Id = :profileId)")
    List<UserResponseDto> findMatchedProfilesAsDto(@Param("profileId") Long profileId);
}
