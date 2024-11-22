package strava.controller;

import strava.dto.TrainingSessionDTO;
import strava.service.TrainingSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Training session created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or user not found")
    })
    @PostMapping("/create")
    public ResponseEntity<String> createSession(
            @Parameter(description = "Details of the training session to create")
            @RequestBody TrainingSessionDTO sessionDTO) {
        try {
            trainingSessionService.createSession(sessionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Training session created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Query user training sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training sessions retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found or no training sessions available")
    })
    @GetMapping("/sessions/{email}")
    public ResponseEntity<List<TrainingSessionDTO>> getSessions(
            @Parameter(description = "Email of the user whose training sessions are to be retrieved")
            @PathVariable String email) {
        List<TrainingSessionDTO> sessions = trainingSessionService.getSessionsByEmail(email);
        if (sessions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(sessions);
    }
}
