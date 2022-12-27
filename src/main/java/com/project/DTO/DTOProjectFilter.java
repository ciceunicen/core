package com.project.DTO;

import lombok.Data;

import java.util.List;

/**
 * Datos que llegan desde front-end que refieren a los filtros.
 */
@Data
public class DTOProjectFilter {

    private List<String> filters;
    private int page;
}
