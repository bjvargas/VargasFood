package bj.vargas.vargasfood.service;

import bj.vargas.vargasfood.interfaces.Notify;
import bj.vargas.vargasfood.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivateService {

	private  Notify notify;

	public CustomerActivateService(final Notify notify) {
		this.notify = notify;
	}

	public void active(final Customer customer) {
		customer.active();

		notify.notify(customer, "Your register is active!");
	}

	@Autowired
	public void setNotify(final Notify notify) {
		this.notify = notify;
	}
}
