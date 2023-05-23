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
    @Column
    private String type;

    public File(String file,String type) {
        this.file = file;
        this.type = type;
    }

    public File() {

    }
}
