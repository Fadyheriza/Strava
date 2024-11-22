package strava.service;

import strava.dto.ChallengeDTO;
import strava.model.Challenge;
import strava.model.UserChallenge;
import strava.repository.ChallengeRepository;
import strava.repository.UserChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserChallengeRepository userChallengeRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository, UserChallengeRepository userChallengeRepository) {
        this.challengeRepository = challengeRepository;
        this.userChallengeRepository = userChallengeRepository;
    }

    public void createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setName(challengeDTO.getName());
        challenge.setStartDate(challengeDTO.getStartDate());
        challenge.setEndDate(challengeDTO.getEndDate());
        challenge.setTargetDistance(challengeDTO.getTargetDistance());
        challenge.setDescription(challengeDTO.getDescription());
        challengeRepository.save(challenge);
    }

    public List<ChallengeDTO> getActiveChallenges() {
        return challengeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void acceptChallenge(Long challengeId, String userEmail) {
        UserChallenge userChallenge = new UserChallenge();
        userChallenge.setChallengeId(challengeId);
        userChallenge.setUserEmail(userEmail);
        userChallengeRepository.save(userChallenge);
    }

    public List<ChallengeDTO> getAcceptedChallenges(String userEmail) {
        List<UserChallenge> userChallenges = userChallengeRepository.findByUserEmail(userEmail);
        List<Long> challengeIds = userChallenges.stream()
                .map(UserChallenge::getChallengeId)
                .collect(Collectors.toList());

        return challengeRepository.findAllById(challengeIds)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ChallengeDTO convertToDTO(Challenge challenge) {
        ChallengeDTO dto = new ChallengeDTO();
        dto.setId(challenge.getId());
        dto.setName(challenge.getName());
        dto.setStartDate(challenge.getStartDate());
        dto.setEndDate(challenge.getEndDate());
        dto.setTargetDistance(challenge.getTargetDistance());
        dto.setDescription(challenge.getDescription());
        dto.setAcceptedUserEmails(challenge.getAcceptedUsers()
                .stream()
                .map(user -> user.getEmail())
                .collect(Collectors.toList())
        );
        return dto;
    }
}
