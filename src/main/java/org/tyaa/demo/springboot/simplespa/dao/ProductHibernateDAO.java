package org.tyaa.demo.springboot.simplespa.dao;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tyaa.demo.springboot.simplespa.entity.Category;
import org.tyaa.demo.springboot.simplespa.entity.Product;
import org.tyaa.demo.springboot.simplespa.entity.QProduct;

import java.util.List;

@Repository
public interface ProductHibernateDAO extends JpaRepository<Product, Long>,
        QuerydslPredicateExecutor<Product>, QuerydslBinderCustomizer<QProduct> {

    @Query( "SELECT p FROM Product p WHERE p.category.id IN :ids" )
    List<Product> findByCategoryIds(
        @Param("ids") List<Long> categoryIds,
        Sort sort
    );

    @Override
    default public void customize(
            QuerydslBindings bindings, QProduct root) {
        bindings.bind(String.class)
            .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.image);
    }
}
