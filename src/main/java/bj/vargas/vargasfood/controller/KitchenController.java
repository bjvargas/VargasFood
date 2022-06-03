package bj.vargas.vargasfood.controller;

import bj.vargas.vargasfood.domain.exception.EntityIsUsed;
import bj.vargas.vargasfood.domain.exception.EntityNotFound;
import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import bj.vargas.vargasfood.domain.service.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private KitchenRepository kitchenRepository;
    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> listRestJSON() {
        return kitchenRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> getKitchenRest(@PathVariable final Long id) {
        final Optional<Kitchen> kitchen = kitchenRepository.findById(id);
        return kitchen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Kitchen>> getKitchensByName(@PathVariable final String name) {
        final List<Kitchen> kitchens = kitchenRepository.name(name);
        if(kitchens.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(kitchens);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Kitchen createRest(@RequestBody final Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> updateRest(@PathVariable final Long id,
                                              @RequestBody final Kitchen kitchen) {
        final Optional<Kitchen> kitchenActual = kitchenRepository.findById(id);

        if (kitchenActual.isPresent()) {
            BeanUtils.copyProperties(kitchen, kitchenActual.get(), "id");
            final Kitchen savedKitchen = kitchenRepository.save(kitchenActual.get());
            return ResponseEntity.ok(savedKitchen);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRest(@PathVariable final Long id) {
        try {
            kitchenService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (final EntityNotFound e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        } catch (final EntityIsUsed e) {
            return ResponseEntity.status(CONFLICT).body(e.getMessage());
        }
    }

    public List<Kitchen> list() {
        final TypedQuery<Kitchen> query = entityManager.createQuery("from Kitchen", Kitchen.class);
        return query.getResultList();
    }

    @Transactional
    public void create(final Kitchen kitchen) {
        entityManager.merge(kitchen);
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
