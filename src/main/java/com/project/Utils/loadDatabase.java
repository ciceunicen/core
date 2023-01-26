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
    ProjectManager pm1 = new ProjectManager("Roberto","Mbappe","segundo@gmail.com","3213213321","docente","Amigo");
    @Bean
    CommandLineRunner initDatabaseProjectManager(@Qualifier("projectManagerRepository") ProjectManagerRepository projectManagerRepository) {
        return args -> {
            log.info("Preloading " + projectManagerRepository.save(pm));
            log.info("Preloading " + projectManagerRepository.save(pm1));
        };
    }

    //Auto carga la tabla necesidades
    Need n1 = new Need("Capacitación",true);
    Need n2 = new Need("Asistencia Técnica",true);
    Need n3 = new Need("Networking",true);
    Need n4 = new Need("Financiamiento",true);
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
    Assistance a1= new Assistance("Técnica",true);
    Assistance a2= new Assistance("Aplicación de lineas de financiamiento",true);
    Assistance a3= new Assistance("Presentación a convocatoria",true);
    @Bean
    CommandLineRunner initDatabaseAssistance(@Qualifier("assistanceRepository") AssistanceRepository assistanceRepository) {
        return args -> {
            log.info("Preloading " + assistanceRepository.save(a1));
            log.info("Preloading " + assistanceRepository.save(a2));
            log.info("Preloading " + assistanceRepository.save(a3));
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
            Project p1 = new Project("Carmen Dauta","Diseño de ropa",new File[]{new File("foto1","image/png"),new File("foto2","image/png")},1l);
            Project p2= new Project("Islands BMX","Tienda de bicicletas y material especializado en el BMX",new File[]{new File("foto3","image/png"),new File("foto4","image/png")},1l);
            Project p3= new Project("Kainomi Solutions","Esta empresa que se dedica a la computación cuántica",new File[]{new File("foto5","image/png"),new File("foto6","image/png")},1l);
            Project p4= new Project("Alpispa","Sustituye los envoltorios de plástico por material natural",new File[]{new File("foto7","image/png"),new File("foto8","image/png")},1l);
            //PROYECTO,ID DEL ESTADIO 5, ID DE LAS ASISTENCIAS 3 ,ID DE LAS NECESIDADES 4,ID PROJECT MANAGER
            log.info("Preloading " + projectServiceImp.addProject(p1,2L,new Long[]{3L},new Long[]{1L,3L},1L));
            log.info("Preloading " + projectServiceImp.addProject(p2,3L,new Long[]{3L,2L},new Long[]{2L,3L},1L));
            log.info("Preloading " + projectServiceImp.addProject(p3,4L,new Long[]{1L,2L},new Long[]{4L,1L},1L));
            log.info("Preloading " + projectServiceImp.addProject(p4,2L,new Long[]{1L},new Long[]{2L},1L));


        };
    }
}
