package bj.vargas.vargasfood.domain.service;

import bj.vargas.vargasfood.domain.exception.EntityIsUsed;
import bj.vargas.vargasfood.domain.exception.EntityNotFound;
import bj.vargas.vargasfood.domain.model.City;
import bj.vargas.vargasfood.domain.model.State;
import bj.vargas.vargasfood.domain.repository.CityRepository;
import bj.vargas.vargasfood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(@RequestBody final City city) {
        final Long idState = city.getState().getId();
        final State state = stateRepository.findById(idState)
                .orElseThrow(() -> new EntityNotFound("State not found"));

        city.setState(state);
        return cityRepository.save(city);
    }

    public void delete(final Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
            throw new EntityNotFound(String.format("There is no City with code %d", id));
        } catch (final DataIntegrityViolationException e) {
            throw new EntityIsUsed(String.format("City cod %d can not be removed, because is used", id));
        }
    }


}
