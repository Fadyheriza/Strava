package strava.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import strava.dto.UserLoginDTO;
import strava.dto.UserRegistrationDTO;
import strava.model.User;
import strava.model.TrainingSession;
import strava.model.Challenge;
import strava.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            return "Error: Email already registered";
        }
        User user = new User();
        user.setEmail(userRegistrationDTO.getEmail());
        user.setName(userRegistrationDTO.getName());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setBirthdate(userRegistrationDTO.getBirthdate());
        user.setWeight(userRegistrationDTO.getWeight());
        user.setHeight(userRegistrationDTO.getHeight());
        user.setMaxHeartRate(userRegistrationDTO.getMaxHeartRate());
        user.setRestHeartRate(userRegistrationDTO.getRestHeartRate());
        user.setProvider(userRegistrationDTO.getProvider());

        userRepository.save(user);
        return "Registration successful";
    }

    public boolean authenticateUser(UserLoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginDTO.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        }
        return false;
    }

    public List<TrainingSession> getUserTrainingSessions(String email) {
        return userRepository.findByEmail(email)
                .map(User::getTrainingSessions)
                .orElse(null);
    }

    public List<Challenge> getAcceptedChallenges(String email) {
        return userRepository.findByEmail(email)
                .map(user -> new ArrayList<>(user.getAcceptedChallenges())) // Convert Set to List
                .orElse(null);
    }
}
