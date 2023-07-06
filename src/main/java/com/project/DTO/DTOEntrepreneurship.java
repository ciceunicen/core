package com.project.DTO;

import java.time.LocalDate;
import java.util.List;

import com.project.entities.Action;
import com.project.entities.File;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class DTOEntrepreneurship {

    private Long id;
    private String title;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    private List<File> files;
    private List<Action> actions;

    public DTOEntrepreneurship(Long id, String title, String description, LocalDate start_date, List<File> files, List<Action> actions) {
        this.id= id;
        this.title=title;
        this.description=description;
        this.start_date=start_date;
        this.files=files;
        this.actions=actions;
    }
}
