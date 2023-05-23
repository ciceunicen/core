package com.project.controller;

import com.project.entities.File;
import com.project.entities.Project;
import com.project.entities.Stage;
import com.project.service.FileService;
import com.project.service.implementation.FileServiceImp;
import com.project.service.implementation.StageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author Colaborativo
 *
 */
@RestController
@RequestMapping("files")
public class FileController {
	@Autowired
    private FileServiceImp fileService;

	public FileController() {
	}
	/*
	 * Obtiene todos los estadios guardados en la base de datos
     * @return Iterable<Stage>.
     */
	@DeleteMapping("/idFile/{id_file}")
	public ResponseEntity<?> deleteFile(@PathVariable("id_file") Long id_file) {
		File response = fileService.deleteFile(id_file);
		System.out.println(response);
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>("404, NOT FOUND", HttpStatus.NOT_FOUND);
	}
	
}
