package com.fabian.streakz.service;

import com.fabian.streakz.exception.ActivityAlreadyExistsException;
import com.fabian.streakz.model.Activity;
import com.fabian.streakz.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity createActivity(Activity activity) {

        String title = activity.getTitle();

        if (getActivityByTitle(title).isPresent())
            throw new ActivityAlreadyExistsException("Activity with title " + title + " already exists.");

        return activityRepository.save(activity);
    }

    public Optional<Activity> getActivityByTitle(String title) {
        return Optional.ofNullable(activityRepository.findByTitle(title));
    }

    public Optional<Activity> getActivityById(UUID id) {
        return activityRepository.findById(id);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }




}
