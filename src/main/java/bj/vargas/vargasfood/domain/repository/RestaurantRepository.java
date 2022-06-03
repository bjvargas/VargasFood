package bj.vargas.vargasfood.domain.repository;

import bj.vargas.vargasfood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByShippingFeeBetween(BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);
    List<Restaurant> findByNameContainingAndKitchenId(@Param("name") String name, @Param("id") Long id);

}
