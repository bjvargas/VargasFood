package bj.vargas.vargasfood.controller;

import bj.vargas.vargasfood.domain.exception.EntityIsUsed;
import bj.vargas.vargasfood.domain.exception.EntityNotFound;
import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.model.Restaurant;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import bj.vargas.vargasfood.domain.repository.RestaurantRepository;
import bj.vargas.vargasfood.domain.service.KitchenService;
import bj.vargas.vargasfood.domain.service.RestaurantService;
import bj.vargas.vargasfood.infraestructure.specification.RestaurantFreeFeeSpec;
import bj.vargas.vargasfood.infraestructure.specification.RestaurantLikelyNameSpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static bj.vargas.vargasfood.infraestructure.specification.RestaurantSpecs.feeFree;
import static bj.vargas.vargasfood.infraestructure.specification.RestaurantSpecs.likelyName;
import static org.springframework.http.HttpStatus.*;

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
        final Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/between/{shippingFeeInitial}/{shippingFeeFinal}")
    public List<Restaurant> getRestaurantListBetween(@PathVariable final BigDecimal shippingFeeInitial,
                                                    @PathVariable final BigDecimal shippingFeeFinal) {
        return restaurantRepository
                .findByShippingFeeBetween(shippingFeeInitial, shippingFeeFinal);
    }

    @GetMapping("/betweenName/{name}/{id}")
    public List<Restaurant> getRestaurantByNameAndKitchen(@PathVariable final String name,
                                                     @PathVariable final Long id) {
        return restaurantRepository
                .findByNameContainingAndKitchenId(name, id);
    }

    @GetMapping("/firstByName/{name}")
    public Optional<Restaurant> getFirstRestaurantByName(@PathVariable final String name) {
        return restaurantRepository
                .findFirstRestaurantByNameContaining(name);
    }

    @GetMapping("/top2ByName/{name}")
    public List<Restaurant> getTop2ByName(@PathVariable final String name) {
        return restaurantRepository
                .findTop2ByNameContaining(name);
    }

    @GetMapping("/countKitchen/{id}")
    public int countKitchen(@PathVariable final Long id) {
        return restaurantRepository
                .countByKitchenId(id);
    }


    @GetMapping("/exists/{name}")
    public boolean existsByName(@PathVariable final String name) {
        return restaurantRepository
                .existsByName(name);
    }

    @GetMapping("/testSDJ/{name}/{shippingFeeInitial}/{shippingFeeFinal}")
    public List<Restaurant> getBetweenSDJ(@PathVariable final String name,
                                          @PathVariable final BigDecimal shippingFeeInitial,
                                          @PathVariable final BigDecimal shippingFeeFinal) {
        return restaurantRepository
                .find(name, shippingFeeInitial, shippingFeeFinal);
    }

    @GetMapping("/testCriteriaQueryAPI/{name}/{shippingFeeInitial}/{shippingFeeFinal}")
    public List<Restaurant> getBetweenCriteriaQueryAPI(@PathVariable final String name,
                                          @PathVariable final BigDecimal shippingFeeInitial,
                                          @PathVariable final BigDecimal shippingFeeFinal) {
        return restaurantRepository
                .findUsingCriteriaAPI(name, shippingFeeInitial, shippingFeeFinal);
    }

    @GetMapping("/testSpec/{name}/")
    public List<Restaurant> getUsingSpec(@PathVariable final String name) {
        var freeFee = new RestaurantFreeFeeSpec();
        var likelyName = new RestaurantLikelyNameSpec(name);


        return restaurantRepository
                .findAll(freeFee.and(likelyName));
    }

    @GetMapping("/testSpecElegant/{name}/")
    public List<Restaurant> getUsingSpecElegant(@PathVariable final String name) {

        return restaurantRepository
                .findAll(feeFree().and(likelyName(name)));
    }

    @GetMapping("/testCustomRepository/")
    public Optional<Restaurant> findUsingCustomRepository() {
        return restaurantRepository.findFirst();
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
        final Optional<Restaurant> restaurantActual = restaurantRepository.findById(id);

        if (restaurantActual.isPresent()) {
            BeanUtils.copyProperties(restaurant, restaurantActual.get(), "id");
            try {
                final Restaurant savedRestaurant = restaurantRepository.save(restaurantActual.get());
                return ResponseEntity.ok(savedRestaurant);
            } catch (final EntityNotFound e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRest(@PathVariable final Long id) {
        try {
            restaurantService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (final EntityNotFound e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        } catch (final EntityIsUsed e) {
            return ResponseEntity.status(CONFLICT).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable final Long id,
                                           @RequestBody final Map<String, Object> fields) {
        final Optional<Restaurant> restaurantActual = restaurantRepository.findById(id);
        if (restaurantActual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        merge(fields, restaurantActual.get());
        return update(id, restaurantActual.get());
    }

    private void merge(final Map<String, Object> dataUpdate, final Restaurant restaurantUpdate) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Restaurant restaurant = objectMapper.convertValue(dataUpdate, Restaurant.class);

        dataUpdate.forEach((property, value) -> {
            final Field field = ReflectionUtils.findField(Restaurant.class, property);
            field.setAccessible(true);

            final Object newValue = ReflectionUtils.getField(field, restaurant);

            ReflectionUtils.setField(field, restaurantUpdate, newValue);
        });
    }

}
