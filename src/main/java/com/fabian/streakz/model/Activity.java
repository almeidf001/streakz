package com.fabian.streakz.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name="activity")
@Getter
@Setter
public class Activity {
    @Id
    private UUID id;

    @NotBlank
    private String title;

    private int streak;

    public Activity() {
        id = UUID.randomUUID();
    }

    public void updateStreak() {
        streak++;
    }

    public void resetStreak() {
        streak = 0;
    }
}
