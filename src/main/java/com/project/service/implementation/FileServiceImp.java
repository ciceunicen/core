package com.project.service.implementation;
import com.project.entities.File;
import com.project.repository.FileRepository;
import com.project.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImp implements FileService {
	@Autowired
	private FileRepository fileRepository;
	@Override
	public File getFile(Long id) {
		return fileRepository.getFile(id);
	}

	@Override
	public File addFile(File file) {
		return fileRepository.save(file);
	}
}
