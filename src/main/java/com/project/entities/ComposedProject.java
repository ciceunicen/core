package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class ComposedProject extends Entrepreneurship {

    @OneToMany(fetch = FetchType.LAZY)
    private List<Entrepreneurship> entrepreneurships;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_Project", nullable = false, unique=true)
    private Project project;

    public ComposedProject(@NotEmpty String title, String description, @NotEmpty Date start_date, @NotEmpty Project project) {
        super(title, description, start_date);
        this.entrepreneurships = new ArrayList<>();
        this.project = project;
    }

    public void addEntrepreneurship(Entrepreneurship e) { this.entrepreneurships.add(e); }
}
