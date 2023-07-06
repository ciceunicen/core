package com.project.repository;

import com.project.entities.Entrepreneurship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepreneurshipRepository extends JpaRepository<Entrepreneurship,Long> {

    @Query("select e from Entrepreneurship e where ((e.title in :data) or (e.description in :data) or (e.start_date in :data))")
    List<Entrepreneurship> findAll(List<String> data);

}
