package com.fabian.streakz.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name="activity")
public class Activity {
//
    @Id
    private UUID id;

    @NotBlank
    private String title;

    private int streak;

    public Activity() {
        id = UUID.randomUUID();
    }

//    public Activity(String title) {
//        this.id = UUID.randomUUID();
//        this.title = title;
//        this.streak = 0;
//    }

    public UUID getId() {
        return id;
    }

//    public void setId(UUID id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public void updateStreak() {
        streak++;
    }

    public void resetStreak() {
        streak = 0;
    }
}
