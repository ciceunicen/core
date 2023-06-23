package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "need")
@Data
public class Need implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Need;
    @Column
    private String needType;
    @Column(name = "is_default")
    private boolean isDefault=false;


    public Need(String needType) {
        this.needType = needType;
    }

    public Need(String needType, boolean is_default) {
        this.needType = needType;
        this.isDefault = is_default;
    }

    public Need() {

    }
}
