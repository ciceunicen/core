package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entities.Entrepreneur;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Long>{

    @Modifying
    @Transactional
    @Query(value = "UPDATE entrepreneur e SET e.deleted=true WHERE e.id=:id",nativeQuery = true)
    void deleteEntrepreneur(Long id);

}
