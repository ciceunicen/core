package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entities.Entrepreneur;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Long>{

    @Modifying
    @Transactional
    @Query(value = "UPDATE entrepreneur e SET e.deleted=true WHERE e.id=:id",nativeQuery = true)
    void deleteEntrepreneur(Long id);

    @Query("select e from Entrepreneur e where (((e.name in :values) or (e.surname in :values) or (e.email in :values) or " +
            "(e.cuil_cuit in :values) or (e.dni in :values) or (e.phone in :values) or (e.location in :values)) and (e.is_deleted = :deleted))")
    List<Entrepreneur> findAll(List<String> values,boolean deleted);
    
    @Query("SELECT e"
    		+ " FROM Entrepreneur e"
    		+ " WHERE e.id_user = :idUser"
    		+ " AND e.is_active IS TRUE")
    Optional<Entrepreneur> findByIdUserAndIsActive(Long idUser);

    @Modifying
    @Transactional
    @Query("UPDATE Entrepreneur e"
    		+ " SET e.is_deleted = true"
    		+ " WHERE e.id_user = :idUser"
    		+ " AND e.is_active IS NOT TRUE")
	void deleteByIdUserAndNoActive(Long idUser);

}
