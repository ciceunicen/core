package com.project.service;

import com.project.entities.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleService {

    public List<Role> findAllRoles();
}
