package bj.vargas.vargasfood.interfaces;

import bj.vargas.vargasfood.domain.model.Customer;

public interface Notify {

    void notify(Customer customer, String message);
}
