package bj.vargas.vargasfood.controller;

import bj.vargas.vargasfood.domain.exception.EntityIsUsed;
import bj.vargas.vargasfood.domain.exception.EntityNotFound;
import bj.vargas.vargasfood.domain.model.City;
import bj.vargas.vargasfood.domain.repository.CityRepository;
import bj.vargas.vargasfood.domain.service.CityService;
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
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> list() {
        return cityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable final Long id) {
        final Optional<City> city = cityRepository.findById(id);
        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody City city) {
        try {
            city = cityService.save(city);
            return ResponseEntity.status(CREATED).body(city);
        } catch (final EntityNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id,
                                    @RequestBody final City city) {
        final Optional<City> cityActual = cityRepository.findById(id);

        if (cityActual.isPresent()) {
            BeanUtils.copyProperties(city, cityActual.get(), "id");
            try {
                final City savedCity = cityRepository.save(cityActual.get());
                return ResponseEntity.ok(savedCity);
            } catch (final EntityNotFound e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRest(@PathVariable final Long id) {
        try {
            cityService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (final EntityNotFound e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        } catch (final EntityIsUsed e) {
            return ResponseEntity.status(CONFLICT).body(e.getMessage());
        }
    }

}
