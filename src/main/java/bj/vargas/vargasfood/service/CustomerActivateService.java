package bj.vargas.vargasfood.service;

import bj.vargas.vargasfood.event.CustomerActivateEvent;
import bj.vargas.vargasfood.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivateService {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	public void active(final Customer customer) {
		customer.active();
		applicationEventPublisher.publishEvent(new CustomerActivateEvent(customer));

	}

}
