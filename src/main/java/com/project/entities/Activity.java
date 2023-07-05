package com.project.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Entity
public class Activity extends Entrepreneurship {

    @Column
    private LocalDate finish_date;

    public Activity(@NotEmpty String title, String description, @NotEmpty LocalDate start_date, @NotEmpty LocalDate finish_date) {
        super(title, description, start_date);
        this.finish_date = finish_date;

    }
    public Activity(){}

}
