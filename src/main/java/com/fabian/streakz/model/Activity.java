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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private UUID uuid;

    @NotBlank
    private String title;

    private int streak;

    public Activity() {
        uuid = UUID.randomUUID();
    }
}
