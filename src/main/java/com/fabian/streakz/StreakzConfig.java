package com.fabian.streakz;

import com.fabian.streakz.model.Activity;
import com.fabian.streakz.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableAsync
@EnableScheduling
@Slf4j
public class StreakzConfig {

    @Autowired
    private ActivityService service;

    @Scheduled(cron = "0 0 0 * * *")
    public void resetStreaks() {
        for (Activity a : service.getAllActivities()) {
            int daysBetween = Days
                .daysBetween(new DateTime(a.getDateIncreased()), new DateTime(new Date()))
                .getDays();

            if (daysBetween > a.getDay_interval())
                service.resetStreak(a.getUuid());
        }
    }

}