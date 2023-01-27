package com.project.service;

import com.project.entities.File;
import org.springframework.stereotype.Component;


@Component
public interface FileService {

	public File getFile(Long id);

	public File addFile(File file);
}
