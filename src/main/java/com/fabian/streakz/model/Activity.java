package com.fabian.streakz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="activity")
@Getter
@Setter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @NotNull
    private UUID uuid;

    @NotBlank
    private String title;

    @Range(min=1)
    private int day_interval;

    private int streak;

    private Date dateIncreased;


    public Activity() {
        uuid = UUID.randomUUID();
        day_interval = 1;
    }
}
