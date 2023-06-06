package com.project.repository;

import com.project.entities.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistanceRepository extends JpaRepository<Assistance,Long>{

    @Query("SELECT a FROM Assistance a where a.id_Assistance=:id")
    Assistance getAssistance(Long id);
}
