package com.project.repository;

import com.project.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    @Query("select a from Action a where ((a.title in :values) or (a.manager in :values) or (a.state in :values) or " +
            "(a.deadline in :values))")
    List<Action> findAll(List<String> values);
}
