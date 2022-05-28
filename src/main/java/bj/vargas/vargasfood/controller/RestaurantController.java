package bj.vargas.vargasfood.controller;

import bj.vargas.vargasfood.domain.model.Restaurant;
import bj.vargas.vargasfood.domain.repository.RestaurantRepository;
import bj.vargas.vargasfood.domain.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getKitchenRest(@PathVariable Long id) {
        final Restaurant restaurant = restaurantRepository.getRestaurant(id);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }


}
