package com.project.controller;

import com.project.DTO.DTOAction;
import com.project.entities.Action;
import com.project.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("acciones")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping()
    public Action postAction(@RequestBody DTOAction a) {
        return this.actionService.postAction(a);
    }
}
