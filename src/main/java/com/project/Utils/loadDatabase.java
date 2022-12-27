package com.project.Utils;

import com.project.entities.ProjectManager;
import com.project.repository.ProjectManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class loadDatabase {
    @Bean
    CommandLineRunner initDatabaseProjectManager(@Qualifier("projectManagerRepository") ProjectManagerRepository projectManagerRepository) {
        return args -> {
            log.info("Preloading " + projectManagerRepository.save(new ProjectManager("Julio","Perez","julio@gmail.com","2494111213","No docente","Sitio web CICE")));
        };
    }
}
