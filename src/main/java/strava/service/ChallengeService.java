package strava.service;

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

    public void createChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    public List<Challenge> getActiveChallenges() {
        return challengeRepository.findAll();
    }

    public void acceptChallenge(Long challengeId, String userEmail) {
        UserChallenge userChallenge = new UserChallenge();
        userChallenge.setChallengeId(challengeId);
        userChallenge.setUserEmail(userEmail);
        userChallengeRepository.save(userChallenge);
    }

    public List<Challenge> getAcceptedChallenges(String userEmail) {
        // Find all UserChallenge records by userEmail
        List<UserChallenge> userChallenges = userChallengeRepository.findByUserEmail(userEmail);

        // Extract challengeIds and retrieve corresponding Challenge entities
        List<Long> challengeIds = userChallenges.stream()
                .map(UserChallenge::getChallengeId)
                .collect(Collectors.toList());

        return challengeRepository.findAllById(challengeIds);
    }
}
