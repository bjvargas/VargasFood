package bj.vargas.vargasfood.controller;

import bj.vargas.vargasfood.domain.model.Kitchen;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class KitchenController {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Kitchen> list() {
        TypedQuery<Kitchen> query = entityManager.createQuery("from Kitchen", Kitchen.class);
        return query.getResultList();
    }

    @Transactional
    public Kitchen create(Kitchen kitchen) {
        return entityManager.merge(kitchen);
    }

    public Kitchen getKitchen(Long id) {
        return entityManager.find(Kitchen.class, id);
    }

    @Transactional
    public void delete(Kitchen kitchen) {
        kitchen = getKitchen(kitchen.getId());
        entityManager.remove(kitchen);
    }


}
