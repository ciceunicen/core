package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Need;

@Repository
public interface NeedRepository extends JpaRepository<Need,Long>{

}
