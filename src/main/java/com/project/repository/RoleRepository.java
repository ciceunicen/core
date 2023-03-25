package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Role;


public interface RoleRepository  extends JpaRepository<Role,Integer>{

}
