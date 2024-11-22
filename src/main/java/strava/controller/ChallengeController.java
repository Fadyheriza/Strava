package strava.controller;

import strava.dto.ChallengeDTO;
import strava.service.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @Operation(summary = "Create a new challenge")
    @PostMapping("/create")
    public ResponseEntity<String> createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        challengeService.createChallenge(challengeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Challenge created");
    }

    @Operation(summary = "Get active challenges")
    @GetMapping("/active")
    public ResponseEntity<List<ChallengeDTO>> getActiveChallenges() {
        List<ChallengeDTO> activeChallenges = challengeService.getActiveChallenges();
        return ResponseEntity.ok(activeChallenges);
    }

    @Operation(summary = "Accept a challenge")
    @PostMapping("/accept")
    public ResponseEntity<String> acceptChallenge(@RequestParam Long challengeId, @RequestParam String userEmail) {
        challengeService.acceptChallenge(challengeId, userEmail);
        return ResponseEntity.ok("Challenge accepted");
    }

    @Operation(summary = "Query accepted challenges for a user")
    @GetMapping("/accepted/{userEmail}")
    public ResponseEntity<List<ChallengeDTO>> getAcceptedChallenges(@PathVariable String userEmail) {
        List<ChallengeDTO> challenges = challengeService.getAcceptedChallenges(userEmail);
        return ResponseEntity.ok(challenges);
    }
}
