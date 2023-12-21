package com.project.DTO;

import lombok.Getter;

@Getter
public class DTOAdministrationRecord{
    private Long idProject;
    private Long idAdmin;
    private String diagnostic;

    public DTOAdministrationRecord(Long idProject, Long idAdmin, String diagnostic) {
        this.idProject = idProject;
        this.idAdmin = idAdmin;
        this.diagnostic = diagnostic;
    }
}