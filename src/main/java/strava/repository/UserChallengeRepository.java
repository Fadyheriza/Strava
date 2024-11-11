package strava.repository;

import strava.model.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {

    List<UserChallenge> findByUserEmail(String userEmail);
}
