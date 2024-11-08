package strava.service;

import strava.dto.UserDTO;
import strava.model.User;
import strava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MockAuthService authService;

    @Autowired
    public UserService(UserRepository userRepository, MockAuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public String registerUser(UserDTO userDTO) {
        if (!authService.authenticate(userDTO.getEmail(), userDTO.getProvider())) {
            return "Error: Email is not registered with " + userDTO.getProvider();
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setBirthdate(userDTO.getBirthdate());
        user.setWeight(userDTO.getWeight());
        user.setHeight(userDTO.getHeight());
        user.setMaxHeartRate(userDTO.getMaxHeartRate());
        user.setRestHeartRate(userDTO.getRestHeartRate());
        user.setProvider(userDTO.getProvider());

        userRepository.save(user);
        return "Registration successful for " + user.getEmail();
    }
}
