package bj.vargas.vargasfood.infraestructure.repository;

import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Kitchen> list() {
        final TypedQuery<Kitchen> query = entityManager.createQuery("from Kitchen", Kitchen.class);
        return query.getResultList();    }

    @Override
    public Kitchen getKitchen(final Long id) {
        return entityManager.find(Kitchen.class, id);
    }

    @Override
    @Transactional
    public Kitchen save(final Kitchen kitchen) {
        return entityManager.merge(kitchen);
    }

    @Override
    @Transactional
    public void remove(final Long id) {
        final Kitchen kitchen = getKitchen(id);
        if (kitchen == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(kitchen);
    }
}
