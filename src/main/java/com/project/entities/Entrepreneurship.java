package com.project.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Entrepreneurship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Builder.Default
    @Column(name="deleted")
    private boolean is_deleted = false;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Date start_date;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrepreneurship", referencedColumnName = "id")
    private List<File> files;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrepreneurship", referencedColumnName = "id")
    private List<Action> actions;

    public  Entrepreneurship() {}

    public Entrepreneurship(@NotEmpty String title, String description, @NotEmpty Date start_date) {
        this.title = title;
        this.description = description;
        this.start_date = start_date;
    }


}
