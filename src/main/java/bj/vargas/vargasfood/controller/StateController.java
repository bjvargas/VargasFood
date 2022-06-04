package bj.vargas.vargasfood.controller;

import bj.vargas.vargasfood.domain.exception.EntityIsUsed;
import bj.vargas.vargasfood.domain.exception.EntityNotFound;
import bj.vargas.vargasfood.domain.model.State;
import bj.vargas.vargasfood.domain.repository.StateRepository;
import bj.vargas.vargasfood.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> list() {
        return stateRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getState(@PathVariable final Long id) {
        final Optional<State> state = stateRepository.findById(id);
        return state.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody State state) {
        try {
            state = stateService.save(state);
            return ResponseEntity.status(CREATED).body(state);
        } catch (final EntityNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id,
                                    @RequestBody final State state) {
        final Optional<State> stateActual = stateRepository.findById(id);

        if (stateActual.isPresent()) {
            BeanUtils.copyProperties(state, stateActual.get(), "id");
            try {
                final State savedState = stateRepository.save(stateActual.get());
                return ResponseEntity.ok(savedState);
            } catch (final EntityNotFound e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRest(@PathVariable final Long id) {
        try {
            stateService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (final EntityNotFound e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        } catch (final EntityIsUsed e) {
            return ResponseEntity.status(CONFLICT).body(e.getMessage());
        }
    }

}
