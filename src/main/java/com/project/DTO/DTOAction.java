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

}
