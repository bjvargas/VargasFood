package bj.vargas.vargasfood.domain.service;

import bj.vargas.vargasfood.domain.exception.EntityIsUsed;
import bj.vargas.vargasfood.domain.exception.EntityNotFound;
import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.model.State;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import bj.vargas.vargasfood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public State save(final State state) {
        return stateRepository.save(state);
    }

    public void delete(final Long id) {
        try {
            stateRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
            throw new EntityNotFound(String.format("There is no State with code %d", id));
        } catch (final DataIntegrityViolationException e) {
            throw new EntityIsUsed(String.format("State cod %d can not be removed, because is used", id));
        }
    }


}
