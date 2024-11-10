package strava.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public class UserDTO {
    private String email;
    private String name;
    private LocalDate birthdate;
    private Double weight;
    private Double height;
    private Integer maxHeartRate;
    private Integer restHeartRate;
    private String provider;
    @Schema(hidden = true)  // Hides from Swagger docs
    private List<TrainingSessionDTO> trainingSessions;
    @Schema(hidden = true)  // Hides from Swagger docs// Updated to use DTO
    private List<ChallengeDTO> acceptedChallenges;      // Updated to use DTO

    // Getters and Setters

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Integer getMaxHeartRate() { return maxHeartRate; }
    public void setMaxHeartRate(Integer maxHeartRate) { this.maxHeartRate = maxHeartRate; }

    public Integer getRestHeartRate() { return restHeartRate; }
    public void setRestHeartRate(Integer restHeartRate) { this.restHeartRate = restHeartRate; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public List<TrainingSessionDTO> getTrainingSessions() { return trainingSessions; }
    public void setTrainingSessions(List<TrainingSessionDTO> trainingSessions) { this.trainingSessions = trainingSessions; }

    public List<ChallengeDTO> getAcceptedChallenges() { return acceptedChallenges; }
    public void setAcceptedChallenges(List<ChallengeDTO> acceptedChallenges) { this.acceptedChallenges = acceptedChallenges; }
}
