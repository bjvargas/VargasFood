package bj.vargas.vargasfood.controller;

import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public List<Kitchen> listRestJSON() {
        return kitchenRepository.list();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Kitchen> listRestXML() {
        return kitchenRepository.list();
    }


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
