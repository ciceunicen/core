package com.project.service;

import com.project.entities.File;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface FileService {

	public File getFile(Long id);

	public File addFile(File file);

	public File deleteFile(Long idFile);

	public List<File> deleteFilesByProject(Long idProject);
}
