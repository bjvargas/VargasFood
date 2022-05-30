package bj.vargas.vargasfood.infraestructure.repository;

import bj.vargas.vargasfood.domain.model.Restaurant;
import bj.vargas.vargasfood.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Restaurant> list() {
        TypedQuery<Restaurant> query = entityManager.createQuery("from Restaurant", Restaurant.class);
        return query.getResultList();    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return entityManager.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return entityManager.merge(restaurant);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Restaurant restaurant = getRestaurant(id);
        entityManager.remove(restaurant);
    }
}
