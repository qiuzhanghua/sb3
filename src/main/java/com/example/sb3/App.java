package com.example.sb3;

import com.example.sb3.domain.QRole;
import com.example.sb3.domain.Role;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
	CommandLineRunner commandLineRunner(EntityManagerFactory emf) {
		return args -> {
			Role role1 = new Role();
			role1.setName("user");
			EntityManager em = emf.createEntityManager();
			jakarta.persistence.EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(role1);
			t.commit();

			// for kapt use
			// QRole role = new QRole("role");
			// // or
			// role = QRole.Companion.getRole();

			// for
			// annotationProcessor("com.querydsl:querydsl-apt:${querydslVersion}:jakarta")
			QRole role = QRole.role;
			JPAQueryFactory queryFactory = new JPAQueryFactory(em);
			List<Role> list = queryFactory.select(role).from(role).fetch();
			System.out.println(list);

			// delete or update
			t = em.getTransaction();
			t.begin();
			queryFactory.delete(role).where(role.name.eq("user")).execute();
			t.commit();
			em.close();

		};
	}

}

// https://www.baeldung.com/intro-to-querydsl
// https://www.jianshu.com/p/8bb33f86d158
// https://juejin.cn/post/6908990542583955469
