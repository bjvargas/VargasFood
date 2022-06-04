package bj.vargas.vargasfood.notify;

import bj.vargas.vargasfood.config.NotifierType;
import bj.vargas.vargasfood.config.UrgencyLevel;
import bj.vargas.vargasfood.interfaces.Notify;
import bj.vargas.vargasfood.domain.model.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@NotifierType(UrgencyLevel.HIGH)
@Component
public class NotifyMailMock implements Notify {


	@Override
	public void notify(final Customer customer, String message) {

		System.out.printf("Mocking %s by mail %s: %s\n",
				customer.getName(), customer.getMail(), message);
	}

}