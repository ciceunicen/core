package com.project.DTO;

import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DTOComposedProjectInsert extends DTOEntrepreneurshipInsert {

    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;
    private Long id_project;

    public DTOComposedProjectInsert(String title, String description, Date start_date, @NonNull Long id_project) {
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.id_project = id_project;
    }
}
