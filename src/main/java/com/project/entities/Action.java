package com.project.entities;

import com.project.entities.utils.ActionState;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotEmpty
    private String title;
    @Column(length=45)
    @NotEmpty
    private String manager;
    @Column
    private ActionState state;
    @Column
    private Date deadline;

    public Action(@NotEmpty String title, @NotEmpty String manager, @NotEmpty ActionState state, @NotEmpty Date deadline) {
        this.title = title;
        this.manager = manager;
        this.state = state;
        this.deadline = deadline;
    }

    public Action(){}
}
