package bj.vargas.vargasfood.infraestructure.specification;

import bj.vargas.vargasfood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> feeFree() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("shippingFee"), BigDecimal.ZERO));
    }

    public static Specification<Restaurant> likelyName(final String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }

}
