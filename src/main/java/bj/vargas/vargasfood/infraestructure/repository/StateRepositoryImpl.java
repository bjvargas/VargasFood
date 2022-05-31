package bj.vargas.vargasfood.infraestructure.repository;

import bj.vargas.vargasfood.domain.model.State;
import bj.vargas.vargasfood.domain.repository.StateRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<State> list() {
        TypedQuery<State> query = entityManager.createQuery("from State", State.class);
        return query.getResultList();    }

    @Override
    public State getState(Long id) {
        return entityManager.find(State.class, id);
    }

    @Override
    @Transactional
    public State save(State state) {
        return entityManager.merge(state);
    }

    @Override
    @Transactional
    public void remove(State state) {
        state = getState(state.getId());
        entityManager.remove(state);
    }
}
