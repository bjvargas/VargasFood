package bj.vargas.vargasfood.domain.repository;

import bj.vargas.vargasfood.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository  {

    List<Kitchen> list();
    Kitchen getKitchen(Long id);
    Kitchen save(Kitchen kitchen);
    void remove(Kitchen kitchen);

}
