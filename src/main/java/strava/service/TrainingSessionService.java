package strava.service;

import strava.dto.TrainingSessionDTO;
import strava.model.TrainingSession;
import strava.model.User;
import strava.repository.TrainingSessionRepository;
import strava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public TrainingSessionService(TrainingSessionRepository trainingSessionRepository, UserRepository userRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
        this.userRepository = userRepository;
    }

    public List<TrainingSessionDTO> getSessionsByEmail(String email) {
        return trainingSessionRepository.findByUserEmail(email)
                .stream()
                .map(this::convertToDTO)  // Convert each TrainingSession to TrainingSessionDTO
                .collect(Collectors.toList());
    }

    public void createSession(TrainingSessionDTO sessionDTO) {
        Optional<User> userOpt = userRepository.findByEmail(sessionDTO.getUserEmail());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        TrainingSession session = new TrainingSession();
        session.setStartTime(sessionDTO.getStartTime());
        session.setEndTime(sessionDTO.getEndTime());
        session.setDistance(sessionDTO.getDistance());
        session.setUser(userOpt.get()); // Set user reference in TrainingSession

        trainingSessionRepository.save(session);
    }

    private TrainingSessionDTO convertToDTO(TrainingSession session) {
        TrainingSessionDTO dto = new TrainingSessionDTO();
        dto.setId(session.getId()); // Include id in DTO
        dto.setStartTime(session.getStartTime());
        dto.setEndTime(session.getEndTime());
        dto.setDistance(session.getDistance());
        dto.setUserEmail(session.getUser().getEmail()); // Only include the email of the user
        return dto;
    }
}
