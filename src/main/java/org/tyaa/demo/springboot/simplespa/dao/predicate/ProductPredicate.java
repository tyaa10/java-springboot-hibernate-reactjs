package org.tyaa.demo.springboot.simplespa.dao.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.commons.lang3.StringUtils;
import org.tyaa.demo.springboot.simplespa.dao.criteria.SearchCriteria;
import org.tyaa.demo.springboot.simplespa.entity.Product;

public class ProductPredicate {

    private SearchCriteria criteria;

    public ProductPredicate(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public BooleanExpression getPredicate() {
        PathBuilder<Product> entityPath =
                new PathBuilder<>(Product.class, "product");
        if (StringUtils.isNumeric(criteria.getValue().toString())) {
            NumberPath<Integer> path =
                    entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case ":":
                    return path.eq(value);
                case ">":
                    return path.goe(value);
                case "<":
                    return path.loe(value);
            }
        }
        else {
            StringPath path = entityPath.getString(criteria.getKey());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
    }
}
