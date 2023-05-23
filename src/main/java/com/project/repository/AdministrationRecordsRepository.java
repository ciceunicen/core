package com.project.repository;

import com.project.entities.AdministrationRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrationRecordsRepository extends JpaRepository<AdministrationRecords,Long> {
    @Query("select ar from AdministrationRecords ar where ar.project.id_Project = :id")
    public Page<AdministrationRecords> getProjectHistory(Pageable p, Long id);
}
