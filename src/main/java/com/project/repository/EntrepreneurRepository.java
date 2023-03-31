package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Entrepreneur;

@Repository
public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Long>{

}
