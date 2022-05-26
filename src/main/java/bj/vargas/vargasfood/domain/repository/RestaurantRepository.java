package bj.vargas.vargasfood.domain.repository;

import bj.vargas.vargasfood.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> list();
    Restaurant getRestaurant(Long id);
    Restaurant save(Restaurant restaurant);
    void remove(Restaurant restaurant);

}
