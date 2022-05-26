package bj.vargas.vargasfood.controller;

import bj.vargas.vargasfood.domain.exception.EntityIsUsed;
import bj.vargas.vargasfood.domain.exception.EntityNotFound;
import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import bj.vargas.vargasfood.domain.service.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private KitchenRepository kitchenRepository;
    @Autowired
    private KitchenService kitchenService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public List<Kitchen> listRestJSON() {
        return kitchenRepository.list();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Kitchen> listRestXML() {
        return kitchenRepository.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> getKitchenRest(@PathVariable Long id) {
        final Kitchen kitchen = kitchenRepository.getKitchen(id);
        if (kitchen != null) {
            return ResponseEntity.ok(kitchen);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Kitchen createRest(@RequestBody final Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> updateRest(@PathVariable final Long id,
                                              @RequestBody final Kitchen kitchen) {
        final Kitchen kitchenActual = kitchenRepository.getKitchen(id);

        if (kitchen != null) {
            BeanUtils.copyProperties(kitchen, kitchenActual, "id");
            kitchenRepository.save(kitchenActual);
            return ResponseEntity.ok(kitchenActual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kitchen> deleteRest(@PathVariable final Long id) {
        try {
            kitchenService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (final EntityNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (final EntityIsUsed e) {
            return ResponseEntity.status(CONFLICT).build();
        }
    }

    public List<Kitchen> list() {
        final TypedQuery<Kitchen> query = entityManager.createQuery("from Kitchen", Kitchen.class);
        return query.getResultList();
    }

    @Transactional
    public Kitchen create(final Kitchen kitchen) {
        return entityManager.merge(kitchen);
    }

    public Kitchen getKitchen(final Long id) {
        return entityManager.find(Kitchen.class, id);
    }

    @Transactional
    public void delete(Kitchen kitchen) {
        kitchen = getKitchen(kitchen.getId());
        entityManager.remove(kitchen);
    }


}
