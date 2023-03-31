package com.project.Utils;

import com.project.entities.*;
import com.project.repository.*;
import com.project.service.implementation.ProjectServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Se utiliza para la auto carga de la base de datos
 */
@Configuration
@Slf4j
public class loadDatabase {
    //Auto carga la tabla Project manager
    ProjectManager pm;
    @Bean
    CommandLineRunner initDatabaseProjectManager(@Qualifier("projectManagerRepository") ProjectManagerRepository projectManagerRepository) throws IOException {
        Iterable<CSVRecord> rows= CSVFormat.DEFAULT.parse(new FileReader("src/main/java/com/project/Files/ProjectManager.csv"));
        rows.iterator().next();
        return args -> {
            for (CSVRecord row:rows) {
                pm = new ProjectManager(row.get(0),row.get(1),row.get(2),row.get(3),row.get(4),row.get(5));
                log.info("Preloading " + projectManagerRepository.save(pm));
            }
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
            Project p;
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader("src/main/java/com/project/Files/Project.json");
            Object obj = jsonParser.parse(reader);
            JSONArray projectsList = (JSONArray) obj;
            for (Object project:projectsList) {
                JSONObject proyecto = (JSONObject)project;
                JSONArray listNeeds = (JSONArray) proyecto.get("needs");
                List<Long>needs = new ArrayList<>();
                for (Object valor:listNeeds) {
                    needs.add((Long) valor);
                }

                JSONArray listAssistances = (JSONArray) proyecto.get("assistances");
                List<Long> assistances = new ArrayList<>();
                for (Object valor:listAssistances) {
                    assistances.add((Long) valor);
                }
                p = new Project(proyecto.get("title").toString(),proyecto.get("description").toString(), (Long) proyecto.get("administrador"));
                log.info("Preloading " + projectServiceImp.addProject(p, (Long) proyecto.get("stage"), assistances,needs, (Long) proyecto.get("id_ProjectManager")));
            }
        };
    }
   User  user1 = new User("ahsdhasdb", "hhhd","juan", "cortes");
   User user2= new User("felipe", "hhhd","juan", "cortes");
   User  user3 = new User("lig", "hhhhhhhhd","juhhhan", "cortes");
   Role r= new Role("admin");
   Role r1= new Role(1);
    @Bean
    CommandLineRunner initDatabaseRole(@Qualifier("roleRepository") RoleRepository roleRepository) {
        return args -> {
            log.info("Preloading " + roleRepository.save(r));
         
        };
    }
    
    //Auto carga la tabla Entrepreneur 
    
    /*Entrepreneur e2 = new Entrepreneur(user2, 1255523, 20102050, 24);
    Entrepreneur e3 = new Entrepreneur(user3, 5555555, 25152555, 55);*/
    @Bean
    CommandLineRunner initDatabaseEntrepreneur(@Qualifier("entrepreneurRepository") EntrepreneurRepository entrepreneurRepository, 
 		   @Qualifier("userRepository") UserRepository userRepository) {
        return args -> {
     	   	Entrepreneur e1 = new Entrepreneur(user1, 1232154, 20202020, 2);
     	   	Entrepreneur e2 = new Entrepreneur(user2, 1122166, 28190420, 3);
     	   	Entrepreneur e3 = new Entrepreneur(user3, 9252254, 55555555, 10);
            log.info("Preloading " + entrepreneurRepository.save(e1));
            log.info("Preloading " + entrepreneurRepository.save(e2));
            log.info("Preloading " + entrepreneurRepository.save(e3));
        };
    }
    
    @Bean
    CommandLineRunner initDatabaseUser(@Qualifier("userRepository") UserRepository userRepository) {
        return args -> {
            log.info("Preloading " + userRepository.save(user1));
            log.info("Preloading " + userRepository.save(user2));
            
            user1.addRole(r1);
            user2.addRole(r1);
            log.info("Preloading " + userRepository.save(user1));
            log.info("Preloading " + userRepository.save(user2));
            log.info("Preloading " + userRepository.save(user3));
            log.info("verificandoo "+user1.getRol().toString());
            log.info("Verificando rol descripto " + r1.toString());
        };
    }
    

   
}

