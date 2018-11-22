package com.fabian.streakz.service;

import com.fabian.streakz.exception.ActivityAlreadyExistsException;
import com.fabian.streakz.exception.ActivityNotFoundException;
import com.fabian.streakz.model.Activity;
import com.fabian.streakz.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Activity getActivityByUUID(UUID id) {
        Optional<Activity> activity = Optional.ofNullable(activityRepository.findByUuid(id));
        if (activity.isPresent())
            return activity.get();
        throw new ActivityNotFoundException(id);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity updateActivity(Activity activity) {
        if (getActivityByTitle(activity.getTitle()).isPresent())
            throw new ActivityAlreadyExistsException("Activity with title " + activity.getTitle() + " already exists.");
        return activityRepository.save(activity);
    }

    public Activity increaseStreak(UUID id) {
        Activity activity = getActivityByUUID(id);
        activity.setStreak(activity.getStreak()+1);
        activity.setDateIncreased(new Date());
        return activityRepository.save(activity);
    }

    private Optional<Activity> getActivityByTitle(String title) {
        return Optional.ofNullable(activityRepository.findByTitle(title));
    }

    public void resetStreak(UUID id) {
        Activity activity = getActivityByUUID(id);
        activity.setStreak(0);
        activity.setDateIncreased(new Date());
        activityRepository.save(activity);
    }
}
