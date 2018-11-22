package com.fabian.streakz.repository;


import com.fabian.streakz.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID> {

    Activity findByTitle(String title);

    Activity findByUuid(UUID id);

}
