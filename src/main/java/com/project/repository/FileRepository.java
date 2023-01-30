package com.project.repository;

import com.project.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,Long>{
    @Query("select f from File f where f.id_File = :id")
    public File getFile(Long id);
}
