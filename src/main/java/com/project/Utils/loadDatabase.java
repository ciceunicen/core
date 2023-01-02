package com.project.Utils;

import com.project.entities.Project;
import com.project.entities.ProjectManager;
import com.project.repository.ProjectManagerRepository;
import com.project.repository.ProjectRepository;
import com.project.service.implementation.ProjectServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Se utiliza para la auto carga de la base de datos
 */
@Configuration
@Slf4j
public class loadDatabase {
    //Auto carga la tabla Project manager
    ProjectManager pm = new ProjectManager("Julio","Perez","julio@gmail.com","2494111213","No docente","Sitio web CICE");
    @Bean
    CommandLineRunner initDatabaseProjectManager(@Qualifier("projectManagerRepository") ProjectManagerRepository projectManagerRepository) {
        return args -> {
            log.info("Preloading " + projectManagerRepository.save(pm));
        };
    }

    //Auto carga la tabla Project
    @Bean
    CommandLineRunner innitDatabaseProject(@Qualifier("projectServiceImp") ProjectServiceImp projectServiceImp){
        return args -> {
            Project p = new Project("Carmen Dauta","Diseño de ropa","Consolidación - Expansión", new String[]{"Aplicación de lineas de financiamiento"},new String[]{"foto1","foto2"},new String[]{"Networking","Capacitación"},1l);
            log.info("Preloading " + projectServiceImp.addProject(p,1L));
        };
    }
}
