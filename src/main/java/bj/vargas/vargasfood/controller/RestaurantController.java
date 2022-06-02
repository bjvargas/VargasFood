package bj.vargas.vargasfood.controller;

import bj.vargas.vargasfood.domain.exception.EntityNotFound;
import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.model.Restaurant;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import bj.vargas.vargasfood.domain.repository.RestaurantRepository;
import bj.vargas.vargasfood.domain.service.KitchenService;
import bj.vargas.vargasfood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable final Long id) {
        final Restaurant restaurant = restaurantRepository.getById(id);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantService.save(restaurant);
            return ResponseEntity.status(CREATED).body(restaurant);
        } catch (final EntityNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id,
                                    @RequestBody final Restaurant restaurant) {
        final Restaurant restaurantActual = restaurantRepository.getById(id);

        if (restaurant != null) {
            BeanUtils.copyProperties(restaurant, restaurantActual, "id");
            try {
                restaurantRepository.save(restaurantActual);
                return ResponseEntity.ok(restaurantActual);
            } catch (final EntityNotFound e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<?> partialUpdate(@PathVariable final Long id,
                                           @RequestBody final Map<String, Object> fields) {
        final Restaurant restaurantActual = restaurantRepository.getById(id);
        if (restaurantActual == null) {
            return ResponseEntity.notFound().build();
        }
        merge(fields, restaurantActual);
        return update(id, restaurantActual);
    }

    private void merge(final Map<String, Object> dataUpdate, final Restaurant restaurantUpdate) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Restaurant restaurant = objectMapper.convertValue(dataUpdate, Restaurant.class);

        dataUpdate.forEach((property, value) -> {
            final Field field = ReflectionUtils.findField(Restaurant.class, property);
            field.setAccessible(true);

            final Object newValue = ReflectionUtils.getField(field, restaurantUpdate);

            ReflectionUtils.setField(field, restaurant, newValue);
        });
    }

}
