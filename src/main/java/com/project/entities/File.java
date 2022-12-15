package com.project.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "file")
@Data
public class File {
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
