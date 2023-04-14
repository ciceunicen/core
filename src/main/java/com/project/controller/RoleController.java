package com.project.controller;

import com.project.entities.Role;
import com.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")//ruta base para las peticiones de los roles
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Peticion que trae todos los elementos que se
     * encuentran el la tabla roles
     * @return todos los roles
     */
    @GetMapping
    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }
}
