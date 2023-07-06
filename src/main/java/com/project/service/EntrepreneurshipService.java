package com.project.service;

import com.project.entities.Entrepreneurship;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntrepreneurshipService {
    List<Entrepreneurship> getAllByFilters(List<String> data);
}
