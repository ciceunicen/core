package com.project.controller;

import com.project.entities.User;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class RoleAuthController {

    @Autowired
    private UserService userService;

    public boolean hasPermission(int role_id) {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User db_user = userService.findById(u.getId());
        if (db_user != null) {
            return u.getRole().getId() == role_id && db_user.getRole().getId() == role_id;
        }
        return false;
    }

    public boolean checkCurrentContextUserId(Long id) {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User db_user = userService.findById(u.getId());
        if (db_user != null) {
            return u.getId() == id && db_user.getId() == id;
        }
        return false;
    }

    public Long getCurrentUserId() {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return u.getId();
    }
}
