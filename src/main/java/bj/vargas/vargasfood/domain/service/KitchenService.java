package bj.vargas.vargasfood.domain.service;

import bj.vargas.vargasfood.domain.exception.EntityIsUsed;
import bj.vargas.vargasfood.domain.exception.EntityNotFound;
import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {
    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(final Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void delete(final Long id) {
        try {
            kitchenRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
            throw new EntityNotFound(String.format("There is no Kitchen with code %d", id));
        } catch (final DataIntegrityViolationException e) {
            throw new EntityIsUsed(String.format("Kitchen cod %d can not be removed, because is used", id));
        }
    }


}
