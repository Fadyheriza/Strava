package strava.controller;

import strava.dto.UserDTO;
import strava.dto.UserLoginDTO;
import strava.dto.UserRegistrationDTO;
import strava.model.TrainingSession;
import strava.model.Challenge;
import strava.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register a new user", description = "Registers a new user with their email, name, and other details. Valid providers are Google and Facebook.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid input or user registration failed")
    })
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @Parameter(description = "User details including email, name, provider, etc.")
            @RequestBody UserRegistrationDTO userRegistrationDTO) {
        String result = userService.registerUser(userRegistrationDTO);
        if (result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "User login", description = "Authenticates a user by email and password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully"),
            @ApiResponse(responseCode = "401", description = "Authentication failed")
    })
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @Parameter(description = "User login credentials")
            @RequestBody UserLoginDTO loginDTO) {
        boolean isAuthenticated = userService.authenticateUser(loginDTO);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @Operation(summary = "Retrieve user training sessions", description = "Fetches all training sessions for the specified user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training sessions retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{email}/training-sessions")
    public ResponseEntity<List<TrainingSession>> getUserTrainingSessions(
            @Parameter(description = "User's email") @PathVariable String email) {
        List<TrainingSession> sessions = userService.getUserTrainingSessions(email);
        if (sessions == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(sessions);
    }

    @Operation(summary = "Retrieve accepted challenges", description = "Fetches all challenges accepted by the specified user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accepted challenges retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{email}/accepted-challenges")
    public ResponseEntity<List<Challenge>> getAcceptedChallenges(
            @Parameter(description = "User's email") @PathVariable String email) {
        List<Challenge> challenges = userService.getAcceptedChallenges(email);
        if (challenges == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(challenges);
    }
}
