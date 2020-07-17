package org.tyaa.demo.springboot.simplespa.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tyaa.demo.springboot.simplespa.entity.Category;
import org.tyaa.demo.springboot.simplespa.entity.Product;

import java.util.List;

@Repository
public interface ProductHibernateDAO extends JpaRepository<Product, Long> {
    @Query( "SELECT p FROM Product p WHERE p.category.id IN :ids" )
    List<Product> findByCategoryIds(
        @Param("ids") List<Long> categoryIds,
        Sort sort
    );
}
