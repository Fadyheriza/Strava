package strava.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "challenge")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate startDate;  // Start date of the challenge

    private LocalDate endDate;    // End date of the challenge

    private Double targetDistance; // Target distance for the challenge

    private String description;    // Description of the challenge

    @ManyToMany(mappedBy = "acceptedChallenges")
    private Set<User> acceptedUsers;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Double getTargetDistance() { return targetDistance; }
    public void setTargetDistance(Double targetDistance) { this.targetDistance = targetDistance; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<User> getAcceptedUsers() { return acceptedUsers; }
    public void setAcceptedUsers(Set<User> acceptedUsers) { this.acceptedUsers = acceptedUsers; }
}
