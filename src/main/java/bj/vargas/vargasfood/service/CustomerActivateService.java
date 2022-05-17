package bj.vargas.vargasfood.service;

import bj.vargas.vargasfood.model.Customer;
import bj.vargas.vargasfood.notify.NotifyMail;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivateService {

	private NotifyMail notifyMail;
	
	public void active(Customer customer) {
		customer.active();

		notifyMail.notify(customer, "Your register is active!");
	}
	
}
