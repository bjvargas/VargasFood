package bj.vargas.vargasfood.infraestructure.repository;

import bj.vargas.vargasfood.domain.model.City;
import bj.vargas.vargasfood.domain.repository.CityRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<City> list() {
        TypedQuery<City> query = entityManager.createQuery("from City", City.class);
        return query.getResultList();    }

    @Override
    public City getCity(Long id) {
        return entityManager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return entityManager.merge(city);
    }

    @Override
    @Transactional
    public void remove(City city) {
        city = getCity(city.getId());
        entityManager.remove(city);
    }
}
