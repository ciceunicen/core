package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Assitance;

@Repository
public interface AssitanceRepository extends JpaRepository<Assitance,Long>{

}
