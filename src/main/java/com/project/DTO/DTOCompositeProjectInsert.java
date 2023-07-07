package com.project.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class DTOCompositeProjectInsert {

    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    public DTOCompositeProjectInsert (String title, String description, LocalDate start_date) {
        this.title = title;
        this.description = description;
        this.start_date = start_date;
    }
}
