package com.project.DTO;

import com.project.entities.Action;
import com.project.entities.Entrepreneurship;
import com.project.entities.File;
import com.project.entities.Project;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class DTOComposedProject extends DTOEntrepreneurship {

    private Long id;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;
    private List<File> files;
    private List<Action> actions;

    private List<Entrepreneurship> entrepreneurships;
    private Project project;

    public DTOComposedProject(Long id, String title, String description, Date start_date, List<File> files, List<Action> actions,
                              List<Entrepreneurship> entrepreneurships, Project project) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.files = files;
        this.actions = actions;
        this.entrepreneurships = entrepreneurships;
        this.project = project;
    }
}
