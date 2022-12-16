package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "file")
@Data
public class File implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_File;
    @Lob
    private String file;

    public File(String file) {
        this.file = file;
    }

    public File() {

    }
}
