package bj.vargas.vargasfood.domain.service;

import bj.vargas.vargasfood.domain.exception.EntityIsUsed;
import bj.vargas.vargasfood.domain.exception.EntityNotFound;
import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.model.Restaurant;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import bj.vargas.vargasfood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private KitchenRepository kitchenRepository;

    public void delete(final Long id) {
        try {
            restaurantRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
            throw new EntityNotFound(String.format("There is no Kitchen with code %d", id));
        } catch (final DataIntegrityViolationException e) {
            throw new EntityIsUsed(String.format("Kitchen cod %d can not be removed, because is used", id));
        }
    }

    public Restaurant save(@RequestBody final Restaurant restaurant) {
        final Long idKitchen = restaurant.getKitchen().getId();
        final Kitchen kitchen = kitchenRepository.findById(idKitchen)
                .orElseThrow(() -> new EntityNotFound("Kitchen not found"));

        restaurant.setKitchen(kitchen);

        return restaurantRepository.save(restaurant);
    }

}
