package strava.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String email;

    private String name;

    private String password;

    private LocalDate birthdate;

    private Double weight;

    private Double height;

    private Integer maxHeartRate;

    private Integer restHeartRate;

    private String provider;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TrainingSession> trainingSessions;

    @ManyToMany
    @JoinTable(
            name = "user_challenge",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "challenge_id")
    )
    private Set<Challenge> acceptedChallenges;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

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

    public List<TrainingSession> getTrainingSessions() { return trainingSessions; }
    public void setTrainingSessions(List<TrainingSession> trainingSessions) { this.trainingSessions = trainingSessions; }

    public Set<Challenge> getAcceptedChallenges() { return acceptedChallenges; }
    public void setAcceptedChallenges(Set<Challenge> acceptedChallenges) { this.acceptedChallenges = acceptedChallenges; }
}
