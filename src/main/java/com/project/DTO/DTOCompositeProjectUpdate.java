package com.project.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DTOCompositeProjectUpdate {

    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;

    public DTOCompositeProjectUpdate (String title, String description, Date start_date) {
        this.title = title;
        this.description = description;
        this.start_date = start_date;
    }
}
