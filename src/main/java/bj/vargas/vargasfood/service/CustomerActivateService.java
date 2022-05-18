package bj.vargas.vargasfood.service;

import bj.vargas.vargasfood.config.NotifierType;
import bj.vargas.vargasfood.config.UrgencyLevel;
import bj.vargas.vargasfood.interfaces.Notify;
import bj.vargas.vargasfood.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerActivateService {

	@Autowired
	@NotifierType(UrgencyLevel.HIGH)
	private Notify notifier;

	public void active(final Customer customer) {
		customer.active();

		notifier.notify(customer, "Your register is active!");

	}

}
