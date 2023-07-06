package com.project.service;

import com.project.DTO.DTOEntrepreneurship;
import com.project.entities.Entrepreneurship;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntrepreneurshipService {
    List<DTOEntrepreneurship> getAllByFilters(List<String> data);
}
