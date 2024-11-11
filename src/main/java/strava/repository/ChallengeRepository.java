package strava.repository;

import strava.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    // Finds challenges where a user with the specified email has accepted them
    List<Challenge> findByAcceptedUsers_Email(String email);
}
