package com.project.DTO;

import com.project.entities.Action;
import com.project.entities.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class DTOActivity extends DTOEntrepreneurship {

    private Long id;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;
    private Date finish_date;
    private List<File> files;
    private List<Action> actions;

    public DTOActivity(Long id, String title, String description, Date start_date, Date finish_date, List<File> files, List<Action> actions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.files = files;
        this.actions = actions;
    }
}
