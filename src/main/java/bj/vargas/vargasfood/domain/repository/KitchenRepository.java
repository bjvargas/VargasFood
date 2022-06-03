package bj.vargas.vargasfood.domain.repository;

import bj.vargas.vargasfood.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    List<Kitchen> name(String name);

}
