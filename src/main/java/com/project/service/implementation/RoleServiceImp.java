package com.project.service.implementation;

import com.project.entities.Role;
import com.project.repository.RoleRepository;
import com.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
