package com.project.DTO;

import lombok.Getter;

@Getter
public class DTOAdministrationRecord{
    private Long idProject;
    private String diagnostic;

    public DTOAdministrationRecord(Long idProject, String diagnostic) {
        this.idProject = idProject;
        this.diagnostic = diagnostic;
    }
}