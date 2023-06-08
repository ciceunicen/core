package com.project.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Activity extends Entrepreneurship {

    @Column
    private Date finish_date;

    public Activity(@NotEmpty String title, String description, @NotEmpty Date start_date, @NotEmpty Date finish_date) {
        //Action act = new Action("lala","pepe","DONE","2022-05-05");
        //ArrayList<Action> listaAction = new ArrayList<Action>();
        super(title, description, start_date);
        this.finish_date = finish_date;

    }
    public Activity(){}

}
