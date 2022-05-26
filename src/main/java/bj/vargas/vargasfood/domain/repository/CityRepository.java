package bj.vargas.vargasfood.domain.repository;

import bj.vargas.vargasfood.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> list();
    City getCity(Long id);
    City save(City city);
    void remove(City city);

}
