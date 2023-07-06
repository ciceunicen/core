package com.project.service.implementation;

import com.project.DTO.DTOActivity;
import com.project.entities.Activity;
import com.project.entities.Entrepreneurship;
import com.project.repository.EntrepreneurshipRepository;
import com.project.service.EntrepreneurshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EntrepreneurshipServiceImp implements EntrepreneurshipService {
    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Override
    public List<Entrepreneurship> getAllByFilters(List<String> data) {
        List<Entrepreneurship> list = entrepreneurshipRepository.findAll(data);
        return list;
    }
}
