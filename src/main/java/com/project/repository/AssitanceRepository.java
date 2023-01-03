package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entities.Assitance;

@Repository
public interface AssitanceRepository extends JpaRepository<Assitance,Long>{
    @Query("SELECT a FROM Assitance a where a.id_Assitance=:id")
    Assitance getAssitance(Long id);
}
