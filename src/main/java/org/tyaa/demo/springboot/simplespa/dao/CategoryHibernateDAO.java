package org.tyaa.demo.springboot.simplespa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tyaa.demo.springboot.simplespa.entity.Category;
import org.tyaa.demo.springboot.simplespa.entity.Payment;

import java.util.List;

@Repository
public interface CategoryHibernateDAO extends JpaRepository<Category, Long> {
}
