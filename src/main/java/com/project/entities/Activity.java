package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Activity extends Entrepreneurship {

    @Column
    private Date finish_date;
    @Column
    private Date start_date;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<File> files;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Action> actions;


    public Activity(@NotEmpty String title, String description, @NotEmpty Date start_date, @NotEmpty Date finish_date) {
        super(title, description);
        this.start_date = start_date;
        this.finish_date = finish_date;

    }

    public Activity(){}

    public void addAction (Action a){
        this.actions.add(a);
    }

    public void addFile ( File f){
        this.files.add(f);
    }

}
