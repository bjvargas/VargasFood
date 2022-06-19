package bj.vargas.vargasfood.domain.repository;

import bj.vargas.vargasfood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant> {

    @Query("from Restaurant r join r.kitchen")
    List<Restaurant> findAll();
    List<Restaurant> findByShippingFeeBetween(BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);

    List<Restaurant> findByNameContainingAndKitchenId(@Param("name") String name, @Param("id") Long id);

    Optional<Restaurant> findFirstRestaurantByNameContaining(@Param("name") String name);

    List<Restaurant> findTop2ByNameContaining(@Param("name") String name);

    boolean existsByName(String name);

    int countByKitchenId(Long id);

    public List<Restaurant> find(String name, BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);

    }
