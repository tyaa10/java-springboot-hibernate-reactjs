package org.tyaa.demo.springboot.simplespa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tyaa.demo.springboot.simplespa.dao.CategoryHibernateDAO;
import org.tyaa.demo.springboot.simplespa.dao.ProductHibernateDAO;
import org.tyaa.demo.springboot.simplespa.dao.RoleHibernateDAO;
import org.tyaa.demo.springboot.simplespa.dao.UserHibernateDAO;
import org.tyaa.demo.springboot.simplespa.entity.Category;
import org.tyaa.demo.springboot.simplespa.entity.Product;
import org.tyaa.demo.springboot.simplespa.entity.Role;
import org.tyaa.demo.springboot.simplespa.entity.User;

import java.math.BigDecimal;

@SpringBootApplication()
public class SpringbootSimplespaApplication {

	@Autowired
	public PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSimplespaApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(
			RoleHibernateDAO roleDAO,
			UserHibernateDAO userDAO,
			CategoryHibernateDAO categoryDAO,
			ProductHibernateDAO productDAO
	) {
		return args -> {
			roleDAO.save(Role.builder().name("admin").build());
			roleDAO.save(Role.builder().name("user").build());
			Role adminRole = roleDAO.findRoleByName("admin");
			Role userRole = roleDAO.findRoleByName("user");
			userDAO.save(
				User.builder()
					.name("admin")
					.password(passwordEncoder.encode("AdminPassword1"))
					.role(adminRole)
					.build()
			);
			userDAO.save(
				User.builder()
					.name("one")
					.password(passwordEncoder.encode("UserPassword1"))
					.role(userRole)
					.build()
			);
			userDAO.save(
				User.builder()
					.name("two")
					.password(passwordEncoder.encode("UserPassword2"))
					.role(userRole)
					.build()
			);
			userDAO.save(
				User.builder()
					.name("three")
					.password(passwordEncoder.encode("UserPassword3"))
					.role(userRole)
					.build()
			);
			Category stockCategory = Category.builder().name("stock").build();
			Category cryptoCategory = Category.builder().name("crypto").build();
			Category eMoneyCategory = Category.builder().name("e-money").build();
			categoryDAO.save(stockCategory);
			categoryDAO.save(cryptoCategory);
			categoryDAO.save(eMoneyCategory);
			Product stockMSFTProduct =
				Product.builder()
					.name("MSFT")
					.description("Microsoft Stock")
					.price(new BigDecimal(203.92))
					.quantity(1000)
					.category(stockCategory)
					.build();
			Product stockORCLProduct =
				Product.builder()
					.name("ORCL")
					.description("Oracle Stock")
					.price(new BigDecimal(55.82))
					.quantity(2000)
					.category(stockCategory)
					.build();
			Product cryptoEthereumProduct =
				Product.builder()
					.name("ETH")
					.description("Ethereum Cryptocurrency")
					.price(new BigDecimal(232.48))
					.quantity(500)
					.category(cryptoCategory)
					.build();
			productDAO.save(stockMSFTProduct);
			productDAO.save(stockORCLProduct);
			productDAO.save(cryptoEthereumProduct);
		};
	}
}
