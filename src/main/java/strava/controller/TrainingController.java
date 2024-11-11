package strava.controller;

import strava.dto.TrainingSessionDTO;
import strava.model.TrainingSession;
import strava.service.TrainingSessionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

    private final TrainingSessionService trainingSessionService;

    @Autowired
    public TrainingController(TrainingSessionService trainingSessionService) {
        this.trainingSessionService = trainingSessionService;
    }

    @Operation(summary = "Create a new training session")
    @PostMapping("/create")
    public ResponseEntity<String> createSession(@RequestBody TrainingSession session) {
        trainingSessionService.createSession(session);
        return ResponseEntity.status(HttpStatus.CREATED).body("Training session created");
    }

    @Operation(summary = "Query user training sessions")
    @GetMapping("/sessions/{email}")
    public ResponseEntity<List<TrainingSessionDTO>> getSessions(@PathVariable String email) {
        List<TrainingSessionDTO> sessions = trainingSessionService.getSessionsByEmail(email);
        return ResponseEntity.ok(sessions);
    }
}
