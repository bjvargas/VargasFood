package bj.vargas.vargasfood.notify;

import bj.vargas.vargasfood.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class NotifyMail {
	
	public void notify(Customer customer, String message) {
		System.out.printf("Notifying by mail %s: %s\n",
				customer.getName(), customer.getMail(), message);
	}
	
}