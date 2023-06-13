package com.project.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class Entrepreneurship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    @NotEmpty
    private String title;
    @Column
    private String description;

    public  Entrepreneurship() {}

    public Entrepreneurship(@NotEmpty String title, String description) {
        this.title = title;
        this.description = description;
    }


}
