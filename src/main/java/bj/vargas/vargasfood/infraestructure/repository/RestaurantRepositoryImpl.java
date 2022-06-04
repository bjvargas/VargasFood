package bj.vargas.vargasfood.infraestructure.repository;

import bj.vargas.vargasfood.domain.model.Restaurant;
import bj.vargas.vargasfood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name, BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal) {

        var jpql = "from Restaurant where name like :name " +
                "and shippingFee between :shippingFeeInitial and :shippingFeeFinal";

        return manager.createQuery(jpql, Restaurant.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("shippingInitial", shippingFeeInitial)
                .setParameter("shippingFinal", shippingFeeFinal)
                .getResultList();

    }

    @Override
    public List<Restaurant> findUsingCriteriaAPI(String name, BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(name)) {
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }
        if (shippingFeeInitial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("shippingFee"), shippingFeeInitial));
        }
        if (shippingFeeFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("shippingFee"), shippingFeeFinal));
        }


        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = manager.createQuery(criteria);

        return query.getResultList();
    }


}
