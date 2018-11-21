package com.fabian.streakz;

import com.fabian.streakz.model.Activity;
import com.fabian.streakz.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableAsync
@EnableScheduling
@Slf4j
public class StreakzConfig {

    @Autowired
    private ActivityRepository repository;

    @Scheduled(cron = "0 0 0 * * *")
    public void resetStreaks() {
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void test() {
        List<Activity> activities = repository.findAll();


//        System.out.println("hello?");
    }

}
