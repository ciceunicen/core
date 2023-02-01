package com.project.service.implementation;
import com.project.entities.File;
import com.project.repository.FileRepository;
import com.project.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

	@Override
	public File deleteFile(Long idFile) {
		if (fileRepository.existsById(idFile)){
			File file = fileRepository.getFile(idFile);
			fileRepository.deleteById(idFile);
			return file;
		}
		return null;
	}

	@Override
	public List<File> deleteFilesByProject(Long id_Project) {
		List<File> files = fileRepository.getFileList(id_Project);
		System.out.println(files);
		if (files.size()>0){
			fileRepository.deleteFilesByProject(id_Project);
			return files;
		}
		return null;
	}
}
