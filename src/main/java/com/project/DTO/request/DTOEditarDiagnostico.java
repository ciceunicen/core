package com.project.DTO.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record DTOEditarDiagnostico(
        @NotNull Long idRecord,
        @NotNull Long idProject,
        @NotNull Long idAdmin,
        @NotBlank String diagnostic) {}
