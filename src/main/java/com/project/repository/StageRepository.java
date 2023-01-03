package com.project.repository;

import com.project.entities.Need;
import com.project.entities.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage,Long>{
    @Query("select s from Stage s where s.id_Stage = :id")
    public Stage getStage(Long id);
}
