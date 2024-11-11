package strava.service;

import strava.dto.TrainingSessionDTO;
import strava.model.TrainingSession;
import strava.repository.TrainingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;

    @Autowired
    public TrainingSessionService(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    public void createSession(TrainingSession session) {
        trainingSessionRepository.save(session);
    }

    public List<TrainingSessionDTO> getSessionsByEmail(String email) {
        return trainingSessionRepository.findByUserEmail(email)
                .stream()
                .map(this::convertToDTO)  // Convert each TrainingSession to TrainingSessionDTO
                .collect(Collectors.toList());
    }

    // Conversion method from TrainingSession to TrainingSessionDTO
    private TrainingSessionDTO convertToDTO(TrainingSession session) {
        TrainingSessionDTO dto = new TrainingSessionDTO();
        dto.setId(session.getId());
        dto.setEmail(session.getUser().getEmail());
        dto.setStartTime(session.getStartTime());
        dto.setEndTime(session.getEndTime());
        dto.setDistance(session.getDistance());
        return dto;
    }
}
