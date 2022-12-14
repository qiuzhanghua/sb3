package com.example.sb3;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.DeleteCriteriaBuilder;
import com.blazebit.persistence.integration.view.spring.EnableEntityViews;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.example.sb3.domain.Role;
import com.example.sb3.domain.RoleView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableEntityViews("com.example")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(EntityManagerFactory emf,
                                               CriteriaBuilderFactory cbf,
                                               EntityViewManager evm) {
        return args -> {
            Role role1 = new Role();
            role1.setName("user");
            EntityManager em = emf.createEntityManager();
            jakarta.persistence.EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(role1);
            t.commit();


            CriteriaBuilder<Role> cb = cbf.create(em, Role.class, "r");
            List<Role> roles = cb.getResultList();
            System.out.println(roles);

            // Use evm
            // still not work
//            t = em.getTransaction();
//            t.begin();
//            RoleView rv = evm.create(RoleView.class);
//            rv.setName("admin");
//            evm.save(em, rv);
//            t.commit();

            CriteriaBuilder<RoleView> roleOnlyBuilder = evm.applySetting(EntityViewSetting.create(RoleView.class), cb);
            List<RoleView> roleViews = roleOnlyBuilder.getResultList();
            System.out.println(roleViews.stream().map(RoleView::getName).collect(Collectors.toList()));

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
