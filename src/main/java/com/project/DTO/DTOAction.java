package com.project.DTO;

import com.project.entities.utils.ActionState;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DTOAction {

    private Long id;
    private String title;
    private String manager;
    private ActionState state;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    public DTOAction(Long id, String title, String manager, ActionState state, Date deadline) {
        this.id = id;
        this.title = title;
        this.manager = manager;
        this.state = state;
        this.deadline = deadline;
    }
}
