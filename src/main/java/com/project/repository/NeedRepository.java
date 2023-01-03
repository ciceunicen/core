package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entities.Need;

@Repository
public interface NeedRepository extends JpaRepository<Need,Long>{
    @Query("select n from Need n where n.id_Need = :id")
    public Need getNeed(Long id);
}
