package com.project.DTO;

import lombok.Getter;

@Getter
public class DTODiagnostic{
    private Long idProject;
    private Long idAdmin;
    private String diagnostic;

    public DTODiagnostic(Long idProject, Long idAdmin, String diagnostic) {
        this.idProject = idProject;
        this.idAdmin = idAdmin;
        this.diagnostic = diagnostic;
    }
}