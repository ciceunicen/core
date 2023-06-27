package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class CompositeProject extends Entrepreneurship {

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Entrepreneurship> entrepreneurships;

    public CompositeProject(@NotEmpty String title, String description, @NotEmpty Date start_date) {
        super(title, description, start_date);
        this.entrepreneurships = new ArrayList<>();
    }

    public CompositeProject(){ }

    public void addEntrepreneurship(Entrepreneurship e) { this.entrepreneurships.add(e); }


    public boolean containsEntrepreneurship(Entrepreneurship e){
        if (this == e) return true;
        for (Entrepreneurship aux: entrepreneurships){
            if  (aux.containsEntrepreneurship(e)){
                return true;
            }
        }
        return false;
    }
}

