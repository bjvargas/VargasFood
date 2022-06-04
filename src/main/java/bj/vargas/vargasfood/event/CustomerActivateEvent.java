package bj.vargas.vargasfood.event;

import bj.vargas.vargasfood.domain.model.Customer;

public class CustomerActivateEvent {
    private  Customer customer;

    public CustomerActivateEvent(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
