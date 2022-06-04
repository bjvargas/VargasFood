package bj.vargas.vargasfood.domain.repository;

import bj.vargas.vargasfood.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {

    List<Restaurant> find(String name, BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);
    List<Restaurant> findUsingCriteriaAPI(String name, BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);
}
