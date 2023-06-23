package com.project.service;

import com.project.DTO.DTOComposedProject;
import com.project.DTO.DTOComposedProjectInsert;
import com.project.entities.Entrepreneurship;
import org.springframework.stereotype.Component;

@Component
public interface ComposedProjectService {

    DTOComposedProject postComposedProject(DTOComposedProjectInsert cp);

    DTOComposedProject getComposedProject(Long id);

    DTOComposedProject addEntrepreneurship(Entrepreneurship e, Long id);
}
