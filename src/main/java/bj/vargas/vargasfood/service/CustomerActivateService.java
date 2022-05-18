package bj.vargas.vargasfood.service;

import bj.vargas.vargasfood.interfaces.Notify;
import bj.vargas.vargasfood.model.Customer;
import org.springframework.stereotype.Component;

public class CustomerActivateService {

	private final Notify notify;

	public CustomerActivateService(final Notify notify) {
		this.notify = notify;
	}

	public void active(final Customer customer) {
		customer.active();

		notify.notify(customer, "Your register is active!");
	}
	
}
