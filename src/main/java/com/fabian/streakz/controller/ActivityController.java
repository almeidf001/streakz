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
    public ResponseEntity<Activity> createActivity(@Valid @RequestBody Activity activity) {
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
        return ResponseEntity.ok(activityService.updateActivity(activity));
    }

    @PutMapping("/up/{id}")
    public ResponseEntity increaseActivityStreak(@PathVariable String id) {
        return ResponseEntity.ok().body(activityService.increaseStreak(UUID.fromString(id)));
    }
}