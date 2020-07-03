package org.tyaa.demo.springboot.simplespa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.tyaa.demo.springboot.simplespa.dao.RoleHibernateDAO;
import org.tyaa.demo.springboot.simplespa.entity.Role;

@SpringBootApplication()
public class SpringbootSimplespaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootSimplespaApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(RoleHibernateDAO roleDAO) {
		return args -> {
			roleDAO.save(Role.builder().name("admin").build());
			roleDAO.save(Role.builder().name("user").build());
		};
	}
}
