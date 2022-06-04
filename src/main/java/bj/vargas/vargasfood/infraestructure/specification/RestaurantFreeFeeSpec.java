package bj.vargas.vargasfood.infraestructure.specification;

import bj.vargas.vargasfood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RestaurantFreeFeeSpec implements Specification<Restaurant> {

    @Override
    public Specification<Restaurant> and(Specification<Restaurant> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Restaurant> or(Specification<Restaurant> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        return criteriaBuilder.equal(root.get("shippingFee"), BigDecimal.ZERO);

    }
}
