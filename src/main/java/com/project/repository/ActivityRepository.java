package com.project.repository;


import com.project.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("select a from Activity a where ((a.title in :data) or (a.description in :data) or (a.start_date in :data) or " +
            "(a.finish_date in :data))")
    List<Activity> findAll(List<String> data);
}
