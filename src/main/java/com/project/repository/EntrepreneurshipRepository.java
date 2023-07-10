package com.project.repository;

import com.project.entities.Activity;
import com.project.entities.Entrepreneurship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepreneurshipRepository extends JpaRepository<Entrepreneurship,Long> {

    @Query("select e from Entrepreneurship e where ((e.title in :data) or (e.description in :data) or (e.start_date in :data))")
    List<Entrepreneurship> findAll(List<String> data);

    @Query("select a from Entrepreneurship e inner join e.entrepreneurships a " +
            "where (e.id=:id) and (a.id in (select id from Activity)) and " +
            "((a.title in :data) or (a.description in :data) or (a.start_date in :data) or (a.finish_date in :data))")
    List<Activity> findAllActivitiesByCompositeProjectId(Long id, List<String> data);

    @Query(value = "select composite_project_id from composite_project_entrepreneurships where entrepreneurships_id=:id", nativeQuery = true)
    List<Long> containsEntrepreneurship(Long id);

}
