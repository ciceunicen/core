package com.project.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DTOActivityUpdate {

    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finish_date;

    public DTOActivityUpdate( String title, String description, Date start_date, Date finish_date) {
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

}
