package com.project.Utils;

import com.project.entities.*;
import com.project.repository.*;
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

    //Auto carga la tabla necesidades
    Need n1 = new Need("Capacitación");
    Need n2 = new Need("Asistencia Técnica");
    Need n3 = new Need("Networking");
    Need n4 = new Need("Financiamiento");
    @Bean
    CommandLineRunner initDatabaseNeed(@Qualifier("needRepository") NeedRepository needRepository) {
        return args -> {
            log.info("Preloading " + needRepository.save(n1));
            log.info("Preloading " + needRepository.save(n2));
            log.info("Preloading " + needRepository.save(n3));
            log.info("Preloading " + needRepository.save(n4));
        };
    }

    //Auto carga la tabla asistencia
    Assitance a1= new Assitance("Técnica");
    Assitance a2= new Assitance("Aplicación de lineas de financiamiento");
    Assitance a3= new Assitance("Presentación a convocatoria");
    @Bean
    CommandLineRunner initDatabaseAssistance(@Qualifier("assitanceRepository") AssitanceRepository assitanceRepository) {
        return args -> {
            log.info("Preloading " + assitanceRepository.save(a1));
            log.info("Preloading " + assitanceRepository.save(a2));
            log.info("Preloading " + assitanceRepository.save(a3));
        };
    }


    //Auto carga la tabla estadios
    Stage s1 = new Stage("Idea negocio");
    Stage s2 = new Stage("Puesta en marcha - Iniciación");
    Stage s3 = new Stage("Formulación de puesta en marcha");
    Stage s4 = new Stage("Consolidación - Expansión");
    Stage s5 = new Stage("Especificación");
    @Bean
    CommandLineRunner initDatabaseStage(@Qualifier("stageRepository") StageRepository stageRepository) {
        return args -> {
            log.info("Preloading " + stageRepository.save(s1));
            log.info("Preloading " + stageRepository.save(s2));
            log.info("Preloading " + stageRepository.save(s3));
            log.info("Preloading " + stageRepository.save(s4));
            log.info("Preloading " + stageRepository.save(s5));
        };
    }




    //Auto carga la tabla Project
    @Bean
    CommandLineRunner innitDatabaseProject(@Qualifier("projectServiceImp") ProjectServiceImp projectServiceImp){
        return args -> {
            Project p1 = new Project("Carmen Dauta","Diseño de ropa",new String[]{"foto1","foto2"},1l);
            Project p2= new Project("Islands BMX","Tienda de bicicletas y material especializado en el BMX",new String[]{"foto3","foto4"},1l);
            Project p3= new Project("Kainomi Solutions","Esta empresa que se dedica a la computación cuántica",new String[]{"foto5","foto6"},1l);
            Project p4= new Project("Alpispa Inc","Sustituye los envoltorios de plástico por material natural",new String[]{"foto7","foto8"},1l);
            //PROYECTO,ID DEL ESTADIO 5, ID DE LAS ASISTENCIAS 3 ,ID DE LAS NECESIDADES 4,ID PROJECT MANAGER
            log.info("Preloading " + projectServiceImp.addProject(p1,2L,new Long[]{3L},new Long[]{1L,3L},1L));
            log.info("Preloading " + projectServiceImp.addProject(p2,3L,new Long[]{3L,2L},new Long[]{2L,3L},1L));
            log.info("Preloading " + projectServiceImp.addProject(p3,4L,new Long[]{1L,2L},new Long[]{4L,1L},1L));
            log.info("Preloading " + projectServiceImp.addProject(p4,2L,new Long[]{1L},new Long[]{2L},1L));


        };
    }
}
