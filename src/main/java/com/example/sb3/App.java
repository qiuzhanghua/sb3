package com.example.sb3;

import com.example.sb3.domain.QRole;
import com.example.sb3.domain.Role;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(EntityManagerFactory emf) {
        return args -> {
            Role user = new Role();
            user.setName("user");
            EntityManager em = emf.createEntityManager();
            jakarta.persistence.EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(user);
            t.commit();
            em.close();
            em = emf.createEntityManager();

            // for kapt use
//            QRole role = new QRole("role");
//            // or
//            role = QRole.Companion.getRole();

            // for annotationProcessor("com.querydsl:querydsl-apt:${querydslVersion}:jakarta")
            QRole role = QRole.role;
            JPAQuery<?> query = new JPAQuery<Void>(em);
            List<Role> list = query.select(role).from(role).fetch();
            System.out.println(list);
            em.close();
        };
    }
}
