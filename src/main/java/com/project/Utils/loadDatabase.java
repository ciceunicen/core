package com.project.Utils;

import com.project.entities.Project;
import com.project.entities.ProjectManager;
import com.project.repository.ProjectManagerRepository;
import com.project.repository.ProjectRepository;
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
    @Bean
    CommandLineRunner initDatabaseProjectManager(@Qualifier("projectManagerRepository") ProjectManagerRepository projectManagerRepository) {
        return args -> {
            log.info("Preloading " + projectManagerRepository.save(new ProjectManager("Julio","Perez","julio@gmail.com","2494111213","No docente","Sitio web CICE")));
        };
    }

    //Auto carga la tabla Project
    @Bean
    CommandLineRunner innitDatabaseProject(@Qualifier("projectRepository") ProjectRepository projectRepository){
        return args -> {
            log.info("Preloading " + projectRepository.save(new Project("Carmen Dauta","Diseño de ropa","Consolidación - Expansión", new String[]{"Aplicación de lineas de financiamiento"},new String[]{"foto1","foto2"},new String[]{"Networking","Capacitación"},1l)));
            log.info("Preloading " + projectRepository.save(new Project("Islands BMX","Tienda de bicicletas y material especializado en el BMX","Idea negocio", new String[]{"Aplicación de lineas de financiamiento","Presentación a convocatoria"},new String[]{"foto3","foto4"},new String[]{"Financiamiento","Capacitación"},1l)));
            log.info("Preloading " + projectRepository.save(new Project("Kainomi Solutions","Esta empresa que se dedica a la computación cuántica","Formulación de puesta en marcha", new String[]{"Técnica","Presentación a convocatoria"},new String[]{"foto5","foto6"},new String[]{"Asistencia Técnica"},1l)));
            log.info("Preloading " + projectRepository.save(new Project("Alpispa","Sustituye los envoltorios de plástico por material natural","Puesta en marcha - Iniciación", new String[]{"Técnica"},new String[]{"foto7","foto8"},new String[]{"Asistencia Técnica","Networking"},1l)));


        };
    }
}
