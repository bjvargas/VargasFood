package bj.vargas.vargasfood.service;

import bj.vargas.vargasfood.interfaces.Notify;
import bj.vargas.vargasfood.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerActivateService {

	@Autowired
	private Notify notifier;

	public void active(final Customer customer) {
		customer.active();

		notifier.notify(customer, "Your register is active!");

	}

}
