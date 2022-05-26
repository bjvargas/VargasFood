package bj.vargas.vargasfood.notify;

import bj.vargas.vargasfood.config.NotifierProperties;
import bj.vargas.vargasfood.config.NotifierType;
import bj.vargas.vargasfood.config.UrgencyLevel;
import bj.vargas.vargasfood.interfaces.Notify;
import bj.vargas.vargasfood.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@NotifierType(UrgencyLevel.HIGH)
@Component
public class NotifyMail implements Notify {

	@Autowired
	private NotifierProperties notifierProperties;


	@Override
	public void notify(final Customer customer, String message) {

		System.out.printf("Notifying %s by mail %s, with Host: %s, and Port: %s: %s\n",
				customer.getName(), customer.getMail(), notifierProperties.getHost(), notifierProperties.getPort(), message);
	}

}