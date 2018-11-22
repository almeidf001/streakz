package com.fabian.streakz.exception;

import java.util.UUID;

public class ActivityNotFoundException extends RuntimeException {
    public ActivityNotFoundException(UUID id) {
        super("Activity with id " + id.toString() + " does not exist.");
    }
}