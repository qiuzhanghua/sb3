package com.example.sb3;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.DeleteCriteriaBuilder;
import com.blazebit.persistence.InsertCriteriaBuilder;
import com.example.sb3.domain.Role;
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
    public CommandLineRunner commandLineRunner(EntityManagerFactory emf,
                                               CriteriaBuilderFactory cbf) {
        return args -> {
            Role role1 = new Role();
            role1.setName("user");
            EntityManager em = emf.createEntityManager();
            jakarta.persistence.EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(role1);
            t.commit();

            CriteriaBuilder<Role> cb = cbf.create(em, Role.class);
            List<Role> roles = cb.getResultList();
            System.out.println(roles);

            t = em.getTransaction();
            t.begin();
            DeleteCriteriaBuilder<Role> dcb = cbf.delete(em, Role.class, "role")
                    .where("role.name").eq("user");
            int count = dcb.executeUpdate();
            System.out.println(count);
            t.commit();
            em.close();
        };
    }
}

// https://www.baeldung.com/blaze-persistence-tutorial
// https://persistence.blazebit.com/
