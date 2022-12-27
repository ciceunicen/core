package com.project.DTO;

import java.util.List;

import com.project.entities.Assitance;
import com.project.entities.File;
import com.project.entities.Need;
import com.project.entities.ProjectManager;
import lombok.Data;

@Data
public class DTOProject {
    
    private Long id_Project;
    private String title;
    private String description;
    private String stage;
    private List<Assitance> assitanceType;
    private ProjectManager projectManager;
    private List<File> files;
    private List<Need> needs;
}
