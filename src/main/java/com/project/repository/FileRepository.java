package com.project.repository;

import com.project.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long>{
    @Query("select f from File f where f.id_File = :id")
    public File getFile(Long id);

    @Query(value = "select f.* from File f where id_Project=:id",nativeQuery = true)
    public List<File> getFileList(Long id);
    @Modifying
    @Transactional
    @Query(value = "delete from File where id_Project=:id_Project",nativeQuery = true)
    void deleteFilesByProject(@Param("id_Project")Long id_Project);
}
