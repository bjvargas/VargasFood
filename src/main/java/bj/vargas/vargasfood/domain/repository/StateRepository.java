package bj.vargas.vargasfood.domain.repository;

import bj.vargas.vargasfood.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> list();
    State getState(Long id);
    State save(State state);
    void remove(State state);

}
