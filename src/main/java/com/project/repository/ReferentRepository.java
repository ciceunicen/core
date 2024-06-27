package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entities.Referent;

@Repository
public interface ReferentRepository extends JpaRepository<Referent,Long> {

    @Query("select r from Referent r where r.id_project= :iDproject" )
    Referent findByIdproject(Long iDproject);
}
