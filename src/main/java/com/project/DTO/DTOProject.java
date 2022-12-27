package com.project.DTO;

import java.util.List;

import com.project.entities.Assitance;
import com.project.entities.File;
import com.project.entities.Need;
import com.project.entities.ProjectManager;

public class DTOProject {
    
    private Long id_Project;
    private String title;
    private String description;
    private String stage;
    private List<Assitance> assitanceType;
    private ProjectManager projectManager;
    private List<File> files;
    private List<Need> needs;
    
    public Long getId_Project() {
        return id_Project;
    }
    public void setId_Project(Long id_Project) {
        this.id_Project = id_Project;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public List<Assitance> getAssitanceType() {
        return assitanceType;
    }
    public void setAssitanceType(List<Assitance> assitanceType) {
        this.assitanceType = assitanceType;
    }
    public ProjectManager getProjectManager() {
        return projectManager;
    }
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }
    public List<File> getFiles() {
        return files;
    }
    public void setFiles(List<File> files) {
        this.files = files;
    }
    public List<Need> getNeeds() {
        return needs;
    }
    public void setNeeds(List<Need> needs) {
        this.needs = needs;
    }
}
