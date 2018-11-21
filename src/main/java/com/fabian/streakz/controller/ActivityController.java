package com.fabian.streakz.controller;


import com.fabian.streakz.model.Activity;
import com.fabian.streakz.repository.ActivityRepository;
import com.fabian.streakz.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity createActivity(@Valid @RequestBody Activity activity) {
        return ResponseEntity.ok(activityService.createActivity(activity));
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getActivities() {
        return ResponseEntity.ok().body(activityService.getAllActivities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Activity>> getActivity(@PathVariable String id) {
        return ResponseEntity.ok().body(activityService.getActivityById(UUID.fromString(id)));
    }

    @PutMapping
    public ResponseEntity updateActivity(@Valid @RequestBody Activity activity) {
        if (!activityRepository.findById(activity.getId()).isPresent())
            return ResponseEntity.badRequest().body("Activity ID not found.");
        if (activityRepository.findByTitle(activity.getTitle()) == null)
            return ResponseEntity.ok(activityRepository.save(activity));

        return ResponseEntity.badRequest().body("Activity with name " + activity.getTitle() + " already exists");
    }

    @PutMapping("/up/{id}")
    public ResponseEntity increaseActivityStreak(@PathVariable String id) {
        if (activityRepository.findById(UUID.fromString(id)).isPresent()) {
            Activity activity = activityRepository.findById(UUID.fromString(id)).get();
            activity.updateStreak();
            return ResponseEntity.ok(activityRepository.save(activity));
        }

        return ResponseEntity.badRequest().body("Activity not found.");
    }

}
